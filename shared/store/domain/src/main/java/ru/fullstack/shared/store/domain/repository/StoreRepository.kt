package ru.fullstack.shared.store.domain.repository

import ru.fullstack.shared.store.domain.entity.Commit
import ru.fullstack.shared.store.domain.entity.Store

interface StoreRepository {

	suspend fun getAllStores(token: String): List<Store>
	suspend fun getAllCommitsFromStore(token: String, username: String, repo: String): List<Commit>
}