package com.ap.csexample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * [ViewModelFactory] which uses Dagger to create the instances.
 * @param viewModel ViewModel to create with using the factory.
 */
class ViewModelFactory<VM : ViewModel> @Inject constructor(private val viewModel: dagger.Lazy<VM>) :
    ViewModelProvider.Factory {
    /**
     * Function that creates a viewmodel through Dagger.
     * @param modelClass Class of the view model to create.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModel.get() as T
}