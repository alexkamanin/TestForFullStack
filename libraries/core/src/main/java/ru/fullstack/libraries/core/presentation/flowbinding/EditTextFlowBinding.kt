package ru.fullstack.libraries.core.presentation.flowbinding

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun EditText.bind(flow: MutableStateFlow<String?>, coroutineScope: CoroutineScope) {
	textOfFlow().onEach { flow.value = it }.launchIn(coroutineScope)
}

fun EditText.textOfFlow(): Flow<String> = callbackFlow {
	val textListener = doAfterTextChanged { offer(it.toString()) }
	awaitClose { removeTextChangedListener(textListener) }
}