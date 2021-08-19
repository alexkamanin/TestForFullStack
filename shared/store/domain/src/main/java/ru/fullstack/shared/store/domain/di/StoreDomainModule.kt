package ru.fullstack.shared.store.domain.di

import org.koin.dsl.module
import ru.fullstack.shared.store.domain.usecase.GetAllCommitsFromStore
import ru.fullstack.shared.store.domain.usecase.GetAllStoresUseCase

internal val storeUseCases = module {

	factory { GetAllStoresUseCase(get()) }
	factory { GetAllCommitsFromStore(get()) }
}

val storeDomainModule = listOf(
	storeUseCases
)