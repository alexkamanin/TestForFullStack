package ru.fullstack.feature.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.fullstack.feature.splash.R
import ru.fullstack.feature.splash.databinding.FragmentSplashBinding
import ru.fullstack.feature.splash.presentation.SplashViewModel
import ru.fullstack.libraries.core.presentation.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashViewModel.EventsListener {

	private val viewModel: SplashViewModel by viewModel()

	override fun getBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentSplashBinding {
		return FragmentSplashBinding.inflate(inflater, container, false)
	}

	override fun goToAuthFragment() {
		findNavController().navigate(R.id.action_splashFragment_to_authFragment)
	}

	override fun goToStoreFragment() {
		findNavController().navigate(R.id.action_splashFragment_to_storeFragment)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initObservers()
	}

	private fun initObservers() {
		viewModel.eventsDispatcher.bind(viewLifecycleOwner, this)
	}
}