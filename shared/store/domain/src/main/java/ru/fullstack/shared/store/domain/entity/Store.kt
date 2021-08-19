package ru.fullstack.shared.store.domain.entity

import java.io.Serializable

data class Store(
	val id: Long,
	val name: String,
	val private: Boolean,
	val forksCount: Int,
	val watchersCount: Int,
	val description: String?,
	val owner: StoreOwner
) : Serializable

data class StoreOwner(
	val id: Long,
	val login: String,
	val avatarUrl: String?
) : Serializable