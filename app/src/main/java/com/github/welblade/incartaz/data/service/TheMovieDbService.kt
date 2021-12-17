package com.github.welblade.incartaz.data.service

import com.github.welblade.incartaz.data.model.Movie
import com.github.welblade.incartaz.data.model.NowPlayingResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbService {
    // https://api.themoviedb.org/3/movie/now_playing?api_key=<<api_key>>&language=en-US&page=1
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Long,
    ): NowPlayingResult

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ):Movie
}