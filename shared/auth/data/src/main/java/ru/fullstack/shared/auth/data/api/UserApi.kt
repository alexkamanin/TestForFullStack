package ru.fullstack.shared.auth.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import ru.fullstack.shared.auth.data.dto.UserDto

interface UserApi {

	@GET("user")
	suspend fun authUser(@Header("Authorization") token: String): UserDto
}