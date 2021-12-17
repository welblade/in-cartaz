package com.github.welblade.incartaz.data.repository

import android.util.Log
import com.github.welblade.incartaz.data.service.TheMovieDbService
import kotlinx.coroutines.flow.flow

class NowPlayingRepositoryImpl(
    private val service: TheMovieDbService
): NowPlayingRepository {
    override suspend fun getNowPlaying(
        apiKey: String,
        language: String,
        page: Int
    ) = flow {
        try {
            val nowPlayingResult = service.getNowPlaying(apiKey, language, page)
            emit(nowPlayingResult)
        }catch(e: Throwable){
            Log.e("Repository", "NowPlaying -> ${e.message}")
        }
    }

}