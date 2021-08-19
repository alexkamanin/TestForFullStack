package ru.fullstack.feature.auth.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fullstack.feature.auth.presentation.AuthViewModel

internal val authViewModel = module {
	viewModel { AuthViewModel(get(), get(), get()) }
}

val authModule = listOf(
	authViewModel
)