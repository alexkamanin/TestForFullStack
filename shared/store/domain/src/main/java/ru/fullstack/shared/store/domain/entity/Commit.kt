package ru.fullstack.shared.store.domain.entity

data class Commit(
	val sha: String,
	val message: String,
	val authorName: String,
	val date: String
)