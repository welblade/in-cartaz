package com.github.welblade.incartaz.domain.di

import com.github.welblade.incartaz.data.di.DataModules
import com.github.welblade.incartaz.domain.GetMovieDetailsUseCase
import com.github.welblade.incartaz.domain.GetNowPlayingUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {
    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { GetNowPlayingUseCase(get()) }
            factory { GetMovieDetailsUseCase(get()) }
        }
    }

}