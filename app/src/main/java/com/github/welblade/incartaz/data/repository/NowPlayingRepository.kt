package com.github.welblade.incartaz.data.repository

import com.github.welblade.incartaz.data.model.NowPlayingResult
import kotlinx.coroutines.flow.Flow

interface NowPlayingRepository {
    suspend fun getNowPlaying(
        apiKey: String,
        language: String,
        page: Int,
    ): Flow<NowPlayingResult>
}