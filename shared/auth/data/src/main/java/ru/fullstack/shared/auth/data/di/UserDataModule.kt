package ru.fullstack.shared.auth.data.di

import org.koin.dsl.module
import retrofit2.Retrofit
import ru.fullstack.shared.auth.data.api.UserApi
import ru.fullstack.shared.auth.data.datasource.UserDataSource
import ru.fullstack.shared.auth.data.datasource.UserDataSourceImpl
import ru.fullstack.shared.auth.data.repository.UserRepositoryImpl
import ru.fullstack.shared.auth.domain.repository.UserRepository

internal val userApi = module {
	single<UserApi> { get<Retrofit>().create(UserApi::class.java) }
}

internal val userDataSource = module {
	factory<UserDataSource> { UserDataSourceImpl(get()) }
}

internal val userRepository = module {
	factory<UserRepository> { UserRepositoryImpl(get()) }
}

val userDataModule = listOf(
	userApi,
	userDataSource,
	userRepository
)