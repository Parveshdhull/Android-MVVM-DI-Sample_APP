package com.parvesh.pixasearch.di

import androidx.lifecycle.ViewModelProvider
import com.parvesh.pixasearch.utils.MyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
  @Binds
  abstract fun bindsViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}