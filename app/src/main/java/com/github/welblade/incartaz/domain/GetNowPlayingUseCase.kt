package com.github.welblade.incartaz.domain

import com.github.welblade.incartaz.BuildConfig
import com.github.welblade.incartaz.core.UseCase
import com.github.welblade.incartaz.data.model.NowPlayingResult
import com.github.welblade.incartaz.data.repository.NowPlayingRepository
import kotlinx.coroutines.flow.Flow

class GetNowPlayingUseCase(
    private val repository: NowPlayingRepository
): UseCase<Int, NowPlayingResult>() {

    override suspend fun execute(param: Int): Flow<NowPlayingResult> {
        val apiKey = BuildConfig.theMovieDbApiKey
        val language = "pt-BR"
        return repository.getNowPlaying(apiKey, language, page=param)
    }

}