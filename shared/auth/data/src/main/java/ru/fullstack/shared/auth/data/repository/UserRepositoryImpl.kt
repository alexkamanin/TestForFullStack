package ru.fullstack.shared.auth.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.fullstack.shared.auth.data.datasource.UserDataSource
import ru.fullstack.shared.auth.data.mapper.toEntity
import ru.fullstack.shared.auth.domain.entity.User
import ru.fullstack.shared.auth.domain.repository.UserRepository

class UserRepositoryImpl(private val dataSource: UserDataSource) : UserRepository {

	override suspend fun authUser(token: String): User = withContext(Dispatchers.IO) {
		dataSource.authUser(token).toEntity()
	}
}