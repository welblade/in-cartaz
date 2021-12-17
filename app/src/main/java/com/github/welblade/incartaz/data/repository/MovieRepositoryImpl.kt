package com.github.welblade.incartaz.data.repository

import com.github.welblade.incartaz.core.exception.TheMovieDbApiException
import com.github.welblade.incartaz.data.model.ErrorResponse
import com.github.welblade.incartaz.data.model.Movie
import com.github.welblade.incartaz.data.service.TheMovieDbService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieRepositoryImpl(
    private val service: TheMovieDbService
):MovieRepository {
    override suspend fun getMovie(
        apiKey: String,
        language: String,
        movieId: Long
    ) = flow {
        try {
            val movie = service.getMovie(
                apiKey = apiKey,
                language = language,
                movieId = movieId
            )
            emit(movie)
        }catch(exception: HttpException){
            val json = exception.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw TheMovieDbApiException(errorResponse)
        }
    }
}