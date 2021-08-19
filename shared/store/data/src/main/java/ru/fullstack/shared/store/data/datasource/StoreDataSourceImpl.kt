package ru.fullstack.shared.store.data.datasource

import ru.fullstack.shared.store.data.api.StoreApi
import ru.fullstack.shared.store.data.dto.CommitDto
import ru.fullstack.shared.store.data.dto.StoreDto

class StoreDataSourceImpl(private val api: StoreApi): StoreDataSource {

	override suspend fun getAllStores(token: String): List<StoreDto> = api.getAllStores(token)

	override suspend fun getAllCommitsFromStore(token: String, username: String, repo: String): List<CommitDto> {
		return api.getAllCommitsFromStore(token, username, repo)
	}
}