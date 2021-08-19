package ru.fullstack.shared.store.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import ru.fullstack.shared.store.data.api.StoreApi
import ru.fullstack.shared.store.data.datasource.StoreDataSource
import ru.fullstack.shared.store.data.datasource.StoreDataSourceImpl
import ru.fullstack.shared.store.data.repository.StoreRepositoryImpl
import ru.fullstack.shared.store.domain.repository.StoreRepository

internal val storeApi = module {

	single<StoreApi> { get<Retrofit>().create(StoreApi::class.java) }
}

internal val storeDataSource = module {

	factory<StoreDataSource> { StoreDataSourceImpl(get()) }
}

internal val storeRepository = module {
	factory<StoreRepository> { StoreRepositoryImpl(get()) }
}

val storeDataModule = listOf(
	storeApi,
	storeDataSource,
	storeRepository
)