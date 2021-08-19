package ru.fullstack.feature.splash.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fullstack.feature.splash.presentation.SplashViewModel

internal val splashViewModel = module {
	viewModel { SplashViewModel(get(), get()) }
}

val splashModule = listOf(
	splashViewModel
)