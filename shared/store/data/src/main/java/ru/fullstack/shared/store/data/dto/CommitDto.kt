package ru.fullstack.shared.store.data.dto

import com.google.gson.annotations.SerializedName

data class CommitDto(
	val sha: String,
	val commit: CommitObjectDto,
)

data class CommitObjectDto(
	val author: AuthorDto,
	val message: String
)

data class AuthorDto(
	val name: String,
	val date: String
)