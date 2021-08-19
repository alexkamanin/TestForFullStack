package ru.fullstack.feature.auth.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.fullstack.feature.auth.databinding.FragmentAuthBinding
import ru.fullstack.libraries.core.presentation.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fullstack.feature.auth.R
import ru.fullstack.feature.auth.presentation.AuthViewModel
import android.content.Intent
import android.net.Uri
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fullstack.libraries.core.presentation.flowbinding.bind

class AuthFragment : BaseFragment<FragmentAuthBinding>(), AuthViewModel.EventsListener {

	private val viewModel: AuthViewModel by viewModel()

	override fun getBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentAuthBinding {
		return FragmentAuthBinding.inflate(inflater, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initObservers()
		initListeners()
	}

	private fun initListeners() {

		binding.authButton.setOnClickListener { viewModel.checkToken() }

		binding.authInfo.setOnClickListener {
			val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.token_documentation_uri)))
			startActivity(myIntent)
		}
	}

	private fun initObservers() {
		viewModel.eventsDispatcher.bind(viewLifecycleOwner, this)
		binding.authEditText.bind(viewModel.token, lifecycleScope)

		viewModel.isNeededEnableAuth.onEach { binding.authButton.isEnabled = it }.launchIn(lifecycleScope)

		viewModel.isNeededProgressBar.onEach {
			binding.authProgressBar.isVisible = it
			binding.authContent.isVisible = !it
		}.launchIn(lifecycleScope)
	}

	override fun goToStoreFragment() {
		findNavController().navigate(R.id.action_authFragment_to_storeFragment)
	}

	override fun onErrorHandling(error: Int) {

		AlertDialog.Builder(requireActivity()).apply {
			setTitle(R.string.error_dialog_title)
			setMessage(error)
			setCancelable(true)
			setPositiveButton(android.R.string.ok, null)
			create()
		}.show()
	}
}