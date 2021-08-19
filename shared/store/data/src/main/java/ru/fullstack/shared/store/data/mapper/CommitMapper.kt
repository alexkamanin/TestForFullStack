package ru.fullstack.shared.store.data.mapper

import ru.fullstack.shared.store.data.dto.CommitDto
import ru.fullstack.shared.store.domain.entity.Commit

fun CommitDto.toEntity() = Commit(
	sha = sha,
	message = commit.message,
	authorName = commit.author.name,
	date = commit.author.date
)