package ru.fullstack.shared.store.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.fullstack.shared.store.data.datasource.StoreDataSource
import ru.fullstack.shared.store.data.mapper.toEntity
import ru.fullstack.shared.store.domain.entity.Commit
import ru.fullstack.shared.store.domain.entity.Store
import ru.fullstack.shared.store.domain.repository.StoreRepository

class StoreRepositoryImpl(private val dataSource: StoreDataSource) : StoreRepository {

	override suspend fun getAllStores(token: String): List<Store> = withContext(Dispatchers.IO) {
		dataSource.getAllStores(token).map { it.toEntity() }
	}

	override suspend fun getAllCommitsFromStore(
		token: String,
		username: String,
		repo: String
	): List<Commit> = withContext(Dispatchers.IO) {
		dataSource.getAllCommitsFromStore(token, username, repo).map { it.toEntity() }
	}
}