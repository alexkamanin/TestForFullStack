package ru.fullstack.shared.auth.domain.usecase

import ru.fullstack.shared.auth.domain.entity.User
import ru.fullstack.shared.auth.domain.repository.UserRepository

class AuthUserUseCase(private val repository: UserRepository) {

	suspend operator fun invoke(token: String): User = repository.authUser(token)
}