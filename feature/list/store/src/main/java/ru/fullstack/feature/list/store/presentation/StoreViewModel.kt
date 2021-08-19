package ru.fullstack.feature.list.store.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.fullstack.feature.list.store.R
import ru.fullstack.libraries.core.navigation.NavigationArgumentsKeys
import ru.fullstack.libraries.core.presentation.event.EventsDispatcher
import ru.fullstack.shared.auth.domain.usecase.AuthUserUseCase
import ru.fullstack.shared.client.usecase.GetClientUseCase
import ru.fullstack.shared.store.domain.entity.Store
import ru.fullstack.shared.store.domain.usecase.GetAllStoresUseCase
import java.lang.Exception
import java.net.UnknownHostException

class StoreViewModel(
	val getClientUseCase: GetClientUseCase,
	val getAllStoresUseCase: GetAllStoresUseCase,
) : ViewModel() {

	private var job: Job? = null

	private val _isNeededProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
	val isNeededProgressBar: StateFlow<Boolean>
		get() = _isNeededProgressBar.asStateFlow()

	private val _isNeededShowError: MutableStateFlow<Boolean> = MutableStateFlow(false)
	val isNeededShowError: StateFlow<Boolean>
		get() = _isNeededShowError.asStateFlow()

	private val _contentList = MutableStateFlow<List<Store>>(listOf())
	val contentList: StateFlow<List<Store>>
		get() = _contentList.asStateFlow()

	interface EventsListener {

		fun goToStoreInfoFragment(bundle: Bundle)
	}

	val eventsDispatcher = EventsDispatcher<EventsListener>()

	init {
		rescheduleJob()
	}

	fun onStoreItemClick(store: Store) {

		eventsDispatcher.dispatchEvent {
			goToStoreInfoFragment(
				Bundle().apply { putSerializable(NavigationArgumentsKeys.STORE, store) }
			)
		}
	}

	fun rescheduleJob() {
		job?.cancel()
		job = viewModelScope.launch {

			try {
				_isNeededProgressBar.value = true
				_isNeededShowError.value = false
				getAllStoresUseCase(getClientUseCase().token).also { _contentList.value = it }
			} catch (e: Exception) {
				_isNeededShowError.value = true
			} finally {
				_isNeededProgressBar.value = false
			}
		}
	}
}