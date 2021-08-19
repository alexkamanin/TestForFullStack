package ru.fullstack.shared.auth.data.datasource

import ru.fullstack.shared.auth.data.dto.UserDto

interface UserDataSource {

	suspend fun authUser(token: String): UserDto
}