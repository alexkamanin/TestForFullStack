package ru.fullstack.libraries.storage.preferences.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.fullstack.libraries.storage.preferences.data.datasource.UserPreferencesDataSource
import ru.fullstack.libraries.storage.preferences.data.datasource.UserPreferencesDataSourceImpl
import ru.fullstack.libraries.storage.preferences.data.repository.UserPreferencesRepositoryImpl
import ru.fullstack.libraries.storage.preferences.repository.UserPreferencesRepository
import ru.fullstack.libraries.storage.preferences.usecase.CreateUserTokenUseCase
import ru.fullstack.libraries.storage.preferences.usecase.GetUserTokenUseCase
import ru.fullstack.libraries.storage.preferences.utils.PreferencesNames

private fun provideUserPreferences(application: Application): SharedPreferences =
    application.getSharedPreferences(PreferencesNames.USER_PREFERENCES, Context.MODE_PRIVATE)

internal val userPreferencesModule = module {

    single<SharedPreferences>(named(PreferencesNames.USER_PREFERENCES)) {
        provideUserPreferences(androidApplication())
    }

    single<UserPreferencesDataSource> {
        UserPreferencesDataSourceImpl(get(named(PreferencesNames.USER_PREFERENCES)))
    }
    single<UserPreferencesRepository> {
        UserPreferencesRepositoryImpl(get())
    }

    factory { CreateUserTokenUseCase(get()) }
    factory { GetUserTokenUseCase(get()) }
}

val preferencesModules = listOf(
    userPreferencesModule
)