package ru.fullstack.shared.auth.data.datasource

import ru.fullstack.shared.auth.data.api.UserApi
import ru.fullstack.shared.auth.data.dto.UserDto

class UserDataSourceImpl(private val api: UserApi): UserDataSource {

	override suspend fun authUser(token: String): UserDto = api.authUser(token)
}