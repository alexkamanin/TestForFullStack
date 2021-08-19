package ru.fullstack.feature.list.store.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.fullstack.feature.list.store.presentation.StoreViewModel
import ru.fullstack.feature.list.store.ui.viewholder.StoreViewHolder
import ru.fullstack.shared.store.domain.entity.Store

class StoreAdapter(
	private val context: Context,
	private val viewModel: StoreViewModel
) : ListAdapter<Store, RecyclerView.ViewHolder>(COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return StoreViewHolder.create(parent, context)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as StoreViewHolder).bind(getItem(position), viewModel)
	}

	companion object {

		private val COMPARATOR = object : DiffUtil.ItemCallback<Store>() {
			override fun areItemsTheSame(
				oldItem: Store,
				newItem: Store
			): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(
				oldItem: Store,
				newItem: Store
			): Boolean {
				return oldItem == newItem
			}
		}
	}
}