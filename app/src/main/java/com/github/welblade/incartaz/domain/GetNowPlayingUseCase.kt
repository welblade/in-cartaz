package com.github.welblade.incartaz.domain

import androidx.paging.PagingData
import com.github.welblade.incartaz.BuildConfig
import com.github.welblade.incartaz.core.UseCase
import com.github.welblade.incartaz.data.model.NowPlayingResult
import com.github.welblade.incartaz.data.model.Result
import com.github.welblade.incartaz.data.repository.NowPlayingRepository
import kotlinx.coroutines.flow.Flow

class GetNowPlayingUseCase(
    private val repository: NowPlayingRepository
): UseCase.NoParam<PagingData<Result>>() {
    override suspend fun execute(): Flow<PagingData<Result>> {
        return repository.getNowPlaying()
    }
}