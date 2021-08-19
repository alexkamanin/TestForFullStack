package ru.fullstack.shared.store.data.datasource

import ru.fullstack.shared.store.data.dto.CommitDto
import ru.fullstack.shared.store.data.dto.StoreDto

interface StoreDataSource {

	suspend fun getAllStores(token: String): List<StoreDto>
	suspend fun getAllCommitsFromStore(token: String, username: String, repo: String): List<CommitDto>
}