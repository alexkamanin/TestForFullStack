package ru.fullstack.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.fullstack.feature.auth.R
import ru.fullstack.libraries.core.presentation.event.EventsDispatcher
import ru.fullstack.libraries.storage.preferences.usecase.CreateUserTokenUseCase
import ru.fullstack.shared.auth.domain.usecase.AuthUserUseCase
import ru.fullstack.shared.client.entity.Client
import ru.fullstack.shared.client.usecase.InitClientUseCase
import java.net.UnknownHostException

class AuthViewModel(
	private val authUserUseCase: AuthUserUseCase,
	private val createUserTokenUseCase: CreateUserTokenUseCase,
	private val initClientUseCase: InitClientUseCase
) : ViewModel() {

	private val _isNeededProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
	val isNeededProgressBar: StateFlow<Boolean>
		get() = _isNeededProgressBar.asStateFlow()

	private val _isNeededEnableAuth: MutableStateFlow<Boolean> = MutableStateFlow(false)
	val isNeededEnableAuth: StateFlow<Boolean>
		get() = _isNeededEnableAuth.asStateFlow()

	val token: MutableStateFlow<String?> = MutableStateFlow(null)

	val eventsDispatcher = EventsDispatcher<EventsListener>()

	interface EventsListener {

		fun onErrorHandling(error: Int)
		fun goToStoreFragment()
	}

	init {

		token.onEach { _isNeededEnableAuth.value = !it.isNullOrEmpty() }.launchIn(viewModelScope)
	}

	fun checkToken() {

		viewModelScope.launch {
			try {
				_isNeededProgressBar.value = true
				authUserUseCase(String.format(Client.TOKEN_FORMAT, token.value!!)).also {
					createUserTokenUseCase(token.value!!)
					initClientUseCase(token.value!!)
					eventsDispatcher.dispatchEvent { goToStoreFragment() }
				}
			} catch (e: HttpException) {
				eventsDispatcher.dispatchEvent {
					onErrorHandling(
						when (e.code()) {
							401  -> R.string.error_authorization
							else -> R.string.error_not_found
						}
					)
				}
			} catch (e: UnknownHostException) {
				eventsDispatcher.dispatchEvent { onErrorHandling(R.string.error_connection) }
			} finally {
				_isNeededProgressBar.value = false
			}
		}
	}
}