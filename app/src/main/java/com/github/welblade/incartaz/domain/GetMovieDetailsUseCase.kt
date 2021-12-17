package com.github.welblade.incartaz.domain

import com.github.welblade.incartaz.BuildConfig
import com.github.welblade.incartaz.core.UseCase
import com.github.welblade.incartaz.data.model.Movie
import com.github.welblade.incartaz.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
):UseCase<Long, Movie>() {
    private val apiKey = BuildConfig.theMovieDbApiKey
    private val language = "pt-BR"
    override suspend fun execute(param: Long): Flow<Movie> {
        return repository.getMovie(apiKey, language, movieId = param)
    }
}