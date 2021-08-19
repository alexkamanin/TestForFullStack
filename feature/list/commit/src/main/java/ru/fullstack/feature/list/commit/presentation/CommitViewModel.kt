package ru.fullstack.feature.list.commit.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.fullstack.shared.client.usecase.GetClientUseCase
import ru.fullstack.shared.store.domain.entity.Commit
import ru.fullstack.shared.store.domain.usecase.GetAllCommitsFromStore
import java.lang.Exception

class CommitViewModel(
	private val storeName: String,
	private val userName: String,
	private val getClientUseCase: GetClientUseCase,
	private val getAllCommitsFromStore: GetAllCommitsFromStore
) : ViewModel() {

	private var job: Job? = null

	private val _isNeededProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
	val isNeededProgressBar: StateFlow<Boolean>
		get() = _isNeededProgressBar.asStateFlow()

	private val _isNeededShowError: MutableStateFlow<Boolean> = MutableStateFlow(false)
	val isNeededShowError: StateFlow<Boolean>
		get() = _isNeededShowError.asStateFlow()

	private val _contentList = MutableStateFlow<List<Commit>>(listOf())
	val contentList: StateFlow<List<Commit>>
		get() = _contentList.asStateFlow()

	init {
		rescheduleJob()
	}

	fun rescheduleJob() {
		job?.cancel()
		job = viewModelScope.launch {
			try {
				_isNeededProgressBar.value = true
				_isNeededShowError.value = false
				_contentList.value = getAllCommitsFromStore(getClientUseCase().token, userName, storeName)
			} catch (e: Exception) {
				_isNeededShowError.value = true
			} finally {
				_isNeededProgressBar.value = false
			}
		}
	}
}