package ru.fullstack.feature.list.store.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fullstack.feature.list.store.presentation.StoreViewModel

internal val storeViewModel = module {

	viewModel { StoreViewModel(get(), get()) }
}

val listStoreModule = listOf(
	storeViewModel
)