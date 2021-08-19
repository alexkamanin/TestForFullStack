package ru.fullstack.libraries.storage.preferences.data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.fullstack.libraries.storage.preferences.utils.PreferencesKeys

class UserPreferencesDataSourceImpl(
	private val preferences: SharedPreferences
) : UserPreferencesDataSource {

	override suspend fun create(token: String) {
		withContext(Dispatchers.IO) {
			preferences.edit(commit = true) { putString(PreferencesKeys.USER_TOKEN, token) }
		}
	}

	override suspend fun getData(): String? = withContext(Dispatchers.IO) {
		return@withContext preferences.getString(PreferencesKeys.USER_TOKEN, null)
	}
}