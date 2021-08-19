package ru.fullstack.shared.auth.domain.repository

import ru.fullstack.shared.auth.domain.entity.User

interface UserRepository {

	suspend fun authUser(token: String): User
}