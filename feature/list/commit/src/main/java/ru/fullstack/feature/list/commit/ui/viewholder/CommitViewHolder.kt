package ru.fullstack.feature.list.commit.ui.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fullstack.feature.list.commit.R
import ru.fullstack.feature.list.commit.databinding.CommitItemBinding
import ru.fullstack.shared.store.domain.entity.Commit

class CommitViewHolder private constructor(
	private val context: Context,
	private val binding: CommitItemBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun create(parent: ViewGroup, context: Context): CommitViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = CommitItemBinding.inflate(inflater, parent, false)
			return CommitViewHolder(context, binding)
		}
	}

	fun bind(commit: Commit) {

		binding.commitSha.text = context.getString(R.string.sha_format,commit.sha)
		binding.commitAuthor.text = context.getString(R.string.author_format,commit.authorName)
		binding.commitMessage.text = context.getString(R.string.message_format,commit.message)
		binding.commitDate.text = context.getString(R.string.date_format,commit.date)
	}
}