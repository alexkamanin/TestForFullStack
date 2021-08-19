package ru.fullstack.feature.list.store.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fullstack.feature.list.store.databinding.FragmentStoreBinding
import ru.fullstack.feature.list.store.presentation.StoreViewModel
import ru.fullstack.libraries.core.presentation.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fullstack.feature.list.store.R
import ru.fullstack.feature.list.store.ui.adapter.StoreAdapter

class StoreFragment : BaseFragment<FragmentStoreBinding>(), StoreViewModel.EventsListener {

	private val viewModel: StoreViewModel by viewModel()

	private lateinit var adapter: StoreAdapter

	override fun getBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentStoreBinding {
		return FragmentStoreBinding.inflate(inflater, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initAdapters()
		initListeners()
		initObservers()
	}

	private fun initListeners() {

		binding.repeatLoad.setOnClickListener { viewModel.rescheduleJob() }
	}

	private fun initAdapters() {
		adapter = StoreAdapter(requireContext(), viewModel)
		binding.storeAdapter.adapter = adapter
	}

	private fun initObservers() {

		viewModel.eventsDispatcher.bind(viewLifecycleOwner, this)

		viewModel.isNeededProgressBar.onEach { binding.commitProgressBar.isVisible = it }.launchIn(lifecycleScope)
		viewModel.isNeededShowError.onEach { binding.connectionError.isVisible = it }.launchIn(lifecycleScope)

		viewModel.contentList.onEach { adapter.submitList(it) }.launchIn(lifecycleScope)
	}

	override fun goToStoreInfoFragment(bundle: Bundle) {

		findNavController().navigate(R.id.action_storeFragment_to_infoFragment, bundle)
	}
}