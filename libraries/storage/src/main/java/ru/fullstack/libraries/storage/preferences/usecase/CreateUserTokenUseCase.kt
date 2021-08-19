package ru.fullstack.libraries.storage.preferences.usecase

import ru.fullstack.libraries.storage.preferences.repository.UserPreferencesRepository

class CreateUserTokenUseCase(private val repository: UserPreferencesRepository) {

    suspend operator fun invoke(token: String) = repository.create(token)
}