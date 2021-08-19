package ru.fullstack.shared.store.domain.usecase

import ru.fullstack.shared.store.domain.entity.Store
import ru.fullstack.shared.store.domain.repository.StoreRepository

class GetAllStoresUseCase(private val repository: StoreRepository) {

	suspend operator fun invoke(token: String): List<Store> = repository.getAllStores(token)
}