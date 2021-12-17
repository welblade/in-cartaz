package com.github.welblade.incartaz.data.repository

import com.github.welblade.incartaz.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovie(
        apiKey: String,
        language: String,
        movieId: Int,
    ):Flow<Movie>
}