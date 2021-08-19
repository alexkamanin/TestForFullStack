package ru.fullstack.feature.list.store.ui.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.fullstack.feature.list.store.R
import ru.fullstack.feature.list.store.databinding.StoreItemBinding
import ru.fullstack.feature.list.store.presentation.StoreViewModel
import ru.fullstack.shared.store.domain.entity.Store

class StoreViewHolder private constructor(
	private val context: Context,
	private val binding: StoreItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun create(parent: ViewGroup, context: Context): StoreViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = StoreItemBinding.inflate(inflater, parent, false)
			return StoreViewHolder(context, binding)
		}
	}

	fun bind(store: Store, viewModel: StoreViewModel) {

		binding.storeName.text = store.name
		binding.privateStore.setImageDrawable(
			ContextCompat.getDrawable(
				context,
				if (store.private) R.drawable.ic_lock_store else R.drawable.ic_unlock_store
			)
		)

		initListeners(viewModel, store)
	}

	private fun initListeners(viewModel: StoreViewModel, store: Store) {

		binding.storeContainer.setOnClickListener {
			viewModel.onStoreItemClick(store)
		}
	}
}