package ru.fullstack.feature.info.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.fullstack.feature.info.R
import ru.fullstack.feature.info.databinding.FragmentInfoBinding
import ru.fullstack.libraries.core.presentation.fragment.BaseFragment
import ru.fullstack.libraries.core.navigation.NavigationArgumentsKeys
import ru.fullstack.libraries.core.presentation.utils.argument
import ru.fullstack.shared.store.domain.entity.Store

class InfoFragment : BaseFragment<FragmentInfoBinding>() {

	private val argStore by argument { getSerializable(NavigationArgumentsKeys.STORE) }

	// Не лучший подход для создания здесь сущности, но плодить viewmodel ради такой простой view не хочется
	private val store: Store
		get() = argStore as Store

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInfoBinding {
		return FragmentInfoBinding.inflate(inflater, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initListeners()
		initLayout()
	}

	private fun initLayout() {

		binding.infoToolbar.title = store.name
		binding.infoAuthor.text = store.owner.login

		binding.infoDescription.text = if (store.description != null) {
			getString(R.string.info_description_format, store.description)
		} else {
			getString(R.string.info_description_format, getString(R.string.info_description_non))
		}

		binding.infoForks.text = getString(R.string.info_forks_format, store.forksCount)
		binding.infoWatchers.text = getString(R.string.info_watchers_format, store.watchersCount)
		Glide
			.with(requireContext())
			.load(store.owner.avatarUrl)
			.error(R.drawable.ic_avatar_default)
			.into(binding.avatar)
	}

	private fun initListeners() {

		binding.infoToolbar.setNavigationOnClickListener { findNavController().popBackStack() }

		binding.infoCommits.setOnClickListener {
			findNavController().navigate(
				R.id.action_infoFragment_to_commitFragment,
				Bundle().apply {
					putString(NavigationArgumentsKeys.STORE_NAME, store.name)
					putString(NavigationArgumentsKeys.USER_NAME, store.owner.login)
				}
			)
		}
	}
}