package ru.fullstack.shared.client.entity

data class Client(
	val token: String
) {
	companion object {
		const val TOKEN_FORMAT = "token %s"
	}
}