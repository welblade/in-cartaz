package com.github.welblade.incartaz.data.repository

import androidx.paging.PagingData
import com.github.welblade.incartaz.data.model.NowPlayingResult
import com.github.welblade.incartaz.data.model.Result
import kotlinx.coroutines.flow.Flow

interface NowPlayingRepository {
    suspend fun getNowPlaying(): Flow<PagingData<Result>>
}