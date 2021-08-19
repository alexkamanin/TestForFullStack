package ru.fullstack.shared.store.data.dto

import com.google.gson.annotations.SerializedName

data class StoreDto(
	val id: Long,
	val name: String,
	val private: Boolean,
	@SerializedName("forks_count")
	val forksCount: Int,
	@SerializedName("watchers_count")
	val watchersCount: Int,
	val description: String?,
	val owner: StoreOwnerDto
)

data class StoreOwnerDto(
	val login: String,
	val id: Long,
	@SerializedName("avatar_url")
	val avatarUrl: String?
)