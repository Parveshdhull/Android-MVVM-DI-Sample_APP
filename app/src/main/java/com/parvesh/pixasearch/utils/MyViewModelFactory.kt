package com.parvesh.pixasearch.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.parvesh.pixasearch.ui.MainActivityViewModel
import javax.inject.Inject
import javax.inject.Provider

class MyViewModelFactory @Inject constructor(private val myViewModelProvider: Provider<MainActivityViewModel>) : ViewModelProvider.Factory {

      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return myViewModelProvider.get() as T
      }

    }