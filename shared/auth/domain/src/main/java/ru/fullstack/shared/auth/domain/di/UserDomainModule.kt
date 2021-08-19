package ru.fullstack.shared.auth.domain.di

import org.koin.dsl.module
import ru.fullstack.shared.auth.domain.usecase.AuthUserUseCase

internal val authUseCase = module {

	factory { AuthUserUseCase(get()) }
}

val userDomainModule = listOf(
	authUseCase
)