package ru.fullstack.libraries.core.presentation.utils

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T> Fragment.argument(crossinline getArgument: Bundle.() -> T): Lazy<T> {
	return lazy(LazyThreadSafetyMode.NONE) {
		val arguments = arguments ?: throw IllegalArgumentException("Fragment has no arguments")
		arguments.getArgument()
	}
}