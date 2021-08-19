package ru.fullstack.feature.list.commit.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.fullstack.feature.list.commit.ui.viewholder.CommitViewHolder
import ru.fullstack.shared.store.domain.entity.Commit

class CommitAdapter(
	private val context: Context,
) : ListAdapter<Commit, RecyclerView.ViewHolder>(COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return CommitViewHolder.create(parent, context)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as CommitViewHolder).bind(getItem(position))
	}

	companion object {

		private val COMPARATOR = object : DiffUtil.ItemCallback<Commit>() {
			override fun areItemsTheSame(
				oldItem: Commit,
				newItem: Commit
			): Boolean {
				return oldItem.sha == newItem.sha
			}

			override fun areContentsTheSame(
				oldItem: Commit,
				newItem: Commit
			): Boolean {
				return oldItem == newItem
			}
		}
	}
}