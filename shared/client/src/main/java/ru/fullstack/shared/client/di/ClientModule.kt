package ru.fullstack.shared.client.di

import org.koin.dsl.module
import ru.fullstack.shared.client.entity.Client
import ru.fullstack.shared.client.usecase.GetClientUseCase
import ru.fullstack.shared.client.usecase.InitClientUseCase

internal val clientSession = module {
	single { (token: String) -> Client(token) }
}

internal val clientUseCases = module {
	factory { InitClientUseCase() }
	factory { GetClientUseCase() }
}

val clientModule = listOf(
	clientSession,
	clientUseCases
)