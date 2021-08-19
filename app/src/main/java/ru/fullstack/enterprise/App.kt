package ru.fullstack.enterprise

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.fullstack.feature.auth.di.authModule
import ru.fullstack.feature.list.commit.di.commitModule
import ru.fullstack.feature.list.store.di.listStoreModule
import ru.fullstack.feature.splash.di.splashModule
import ru.fullstack.libraries.network.core.di.retrofitModule
import ru.fullstack.libraries.storage.preferences.di.preferencesModules
import ru.fullstack.shared.auth.data.di.userDataModule
import ru.fullstack.shared.auth.domain.di.userDomainModule
import ru.fullstack.shared.client.di.clientModule
import ru.fullstack.shared.store.data.di.storeDataModule
import ru.fullstack.shared.store.domain.di.storeDomainModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)
			androidFileProperties()

			modules(retrofitModule)
			modules(preferencesModules)

			modules(storeDataModule)
			modules(storeDomainModule)

			modules(userDataModule)
			modules(userDomainModule)

			modules(splashModule)
			modules(authModule)
			modules(listStoreModule)
			modules(commitModule)

			modules(clientModule)
		}
	}
}