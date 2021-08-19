package ru.fullstack.libraries.storage.preferences.data.datasource

interface UserPreferencesDataSource {

    suspend fun create(token: String)
    suspend fun getData(): String?
}