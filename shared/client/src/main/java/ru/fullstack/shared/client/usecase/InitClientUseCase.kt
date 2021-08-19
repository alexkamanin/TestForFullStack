package ru.fullstack.shared.client.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin
import ru.fullstack.shared.client.entity.Client

class InitClientUseCase {

	suspend operator fun invoke(token: String) {
		withContext(Dispatchers.IO) {
			getKoin().get<Client> { parametersOf(String.format(Client.TOKEN_FORMAT, token)) }
		}
	}
}