package ru.fullstack.feature.list.commit.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fullstack.feature.list.commit.presentation.CommitViewModel

internal val commitViewModel = module {

	viewModel { (storeName: String, userName: String) -> CommitViewModel(storeName, userName, get(), get()) }
}

val commitModule = listOf(
	commitViewModel
)