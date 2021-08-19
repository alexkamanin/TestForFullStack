package ru.fullstack.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.fullstack.libraries.core.presentation.event.EventsDispatcher
import ru.fullstack.libraries.storage.preferences.usecase.GetUserTokenUseCase
import ru.fullstack.shared.client.usecase.InitClientUseCase

class SplashViewModel(
	private val getUserTokenUseCase: GetUserTokenUseCase,
	private val initClientUseCase: InitClientUseCase
) : ViewModel() {

	val eventsDispatcher = EventsDispatcher<EventsListener>()

	interface EventsListener {

		fun goToAuthFragment()
		fun goToStoreFragment()
	}

	init {
		viewModelScope.launch {
			delay(1500) // Символическая задержка (можно убрать)
			getUserTokenUseCase()?.let { token ->
				initClientUseCase(token)
				eventsDispatcher.dispatchEvent { goToStoreFragment() }
			} ?: eventsDispatcher.dispatchEvent { goToAuthFragment() }
		}
	}
}