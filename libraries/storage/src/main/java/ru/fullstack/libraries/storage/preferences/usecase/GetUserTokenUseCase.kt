package ru.fullstack.libraries.storage.preferences.usecase

import ru.fullstack.libraries.storage.preferences.repository.UserPreferencesRepository

class GetUserTokenUseCase(private val repository: UserPreferencesRepository) {

    suspend operator fun invoke(): String? = repository.getData()
}