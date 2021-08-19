package ru.fullstack.shared.store.domain.usecase

import ru.fullstack.shared.store.domain.entity.Commit
import ru.fullstack.shared.store.domain.repository.StoreRepository

class GetAllCommitsFromStore(private val repository: StoreRepository) {

	suspend operator fun invoke(
		token: String,
		username: String,
		repo: String
	): List<Commit> = repository.getAllCommitsFromStore(token, username, repo)
}