package ru.fullstack.libraries.core.presentation.event

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

class EventsDispatcher<T> : LifecycleObserver {

	private var eventsListener: T? = null
	private var activeListener: T? = null
	private var eventsList = LinkedList<T.() -> Unit>()

	fun bind(lifecycleOwner: LifecycleOwner, listener: T) {
		eventsListener = listener
		lifecycleOwner.lifecycle.addObserver(this)
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
	fun connectListener() {
		activeListener = eventsListener
		activeListener?.let { listener ->
			eventsList.forEach { event ->
				event(listener)
			}
			eventsList.clear()
		}
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	fun disconnectListener() {
		activeListener = null
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	fun clear(lifecycleOwner: LifecycleOwner) {
		eventsListener = null
		activeListener = null
		lifecycleOwner.lifecycle.removeObserver(this)
	}

	fun dispatchEvent(event: T.() -> Unit) {
		activeListener?.also(event) ?: eventsList.add(event)
	}
}