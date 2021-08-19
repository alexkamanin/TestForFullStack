package ru.fullstack.feature.list.commit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fullstack.feature.list.commit.databinding.FragmentCommitBinding
import ru.fullstack.feature.list.commit.presentation.CommitViewModel
import ru.fullstack.libraries.core.presentation.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.fullstack.feature.list.commit.ui.adapter.CommitAdapter
import ru.fullstack.libraries.core.navigation.NavigationArgumentsKeys
import ru.fullstack.libraries.core.presentation.utils.argument

class CommitFragment : BaseFragment<FragmentCommitBinding>() {

	private val argStoreName by argument { getString(NavigationArgumentsKeys.STORE_NAME) }
	private val argUserName by argument { getString(NavigationArgumentsKeys.USER_NAME) }

	private val viewModel: CommitViewModel by viewModel { parametersOf(argStoreName, argUserName) }

	private lateinit var adapter: CommitAdapter

	override fun getBinding(
		inflater: LayoutInflater,
		container: ViewGroup?
	): FragmentCommitBinding {
		return FragmentCommitBinding.inflate(inflater, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initAdapters()
		initListeners()
		initObservers()
	}

	private fun initListeners() {

		binding.commitToolbar.setNavigationOnClickListener { findNavController().popBackStack() }

		binding.repeatLoad.setOnClickListener { viewModel.rescheduleJob() }
	}

	private fun initAdapters() {

		adapter = CommitAdapter(requireContext())
		binding.commitAdapter.adapter = adapter
	}

	private fun initObservers() {

		viewModel.isNeededProgressBar.onEach { binding.commitProgressBar.isVisible = it }.launchIn(lifecycleScope)
		viewModel.isNeededShowError.onEach { binding.connectionError.isVisible = it }.launchIn(lifecycleScope)

		viewModel.contentList.onEach { adapter.submitList(it) }.launchIn(lifecycleScope)
	}
}