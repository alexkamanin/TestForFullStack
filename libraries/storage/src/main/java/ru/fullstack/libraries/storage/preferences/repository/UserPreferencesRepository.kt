package ru.fullstack.libraries.storage.preferences.repository

interface UserPreferencesRepository {

	suspend fun create(token: String)
	suspend fun getData(): String?
}