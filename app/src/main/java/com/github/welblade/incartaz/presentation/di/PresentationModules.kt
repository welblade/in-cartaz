package com.github.welblade.incartaz.presentation.di

import com.github.welblade.incartaz.presentation.MainViewModel
import com.github.welblade.incartaz.presentation.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModules {
    fun load(){
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { MainViewModel(get()) }
            viewModel { DetailsViewModel(get()) }
        }
    }
}