package com.github.welblade.incartaz.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.github.welblade.incartaz.data.model.NowPlayingResult
import com.github.welblade.incartaz.data.model.Result
import com.github.welblade.incartaz.domain.GetNowPlayingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val nowPlayingUseCase: GetNowPlayingUseCase
): ViewModel() {

    suspend fun getMovies(): Flow<PagingData<Result>> {
        return nowPlayingUseCase().cachedIn(viewModelScope)
    }

}