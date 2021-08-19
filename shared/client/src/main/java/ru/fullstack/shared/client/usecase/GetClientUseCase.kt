package ru.fullstack.shared.client.usecase

import org.koin.java.KoinJavaComponent.getKoin
import ru.fullstack.shared.client.entity.Client

class GetClientUseCase {

	suspend operator fun invoke(): Client = getKoin().get<Client>()
}