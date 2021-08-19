package ru.fullstack.shared.store.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.fullstack.shared.store.data.dto.CommitDto
import ru.fullstack.shared.store.data.dto.StoreDto

interface StoreApi {

	@GET("user/repos")
	suspend fun getAllStores(@Header("Authorization") token: String): List<StoreDto>

	@GET("repos/{username}/{repository}/commits")
	suspend fun getAllCommitsFromStore(
		@Header("Authorization") token: String,
		@Path("username") username: String,
		@Path("repository") repo: String
	) : List<CommitDto>
}