package ru.fullstack.libraries.storage.preferences.data.repository

import ru.fullstack.libraries.storage.preferences.data.datasource.UserPreferencesDataSource
import ru.fullstack.libraries.storage.preferences.repository.UserPreferencesRepository

class UserPreferencesRepositoryImpl(
    private val dataSource: UserPreferencesDataSource
) : UserPreferencesRepository {

    override suspend fun create(token: String) = dataSource.create(token)

    override suspend fun getData(): String? = dataSource.getData()
}