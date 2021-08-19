package ru.fullstack.shared.store.data.mapper

import ru.fullstack.shared.store.data.dto.StoreDto
import ru.fullstack.shared.store.data.dto.StoreOwnerDto
import ru.fullstack.shared.store.domain.entity.Store
import ru.fullstack.shared.store.domain.entity.StoreOwner

fun StoreDto.toEntity() = Store(
	id = id,
	name = name,
	private = private,
	forksCount = forksCount,
	description = description,
	watchersCount = watchersCount,
	owner = owner.toEntity()
)

fun StoreOwnerDto.toEntity() = StoreOwner(
	id = id,
	login = login,
	avatarUrl = avatarUrl
)