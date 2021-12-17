package com.github.welblade.incartaz.data.service

import com.github.welblade.incartaz.data.model.Movie
import com.github.welblade.incartaz.data.model.NowPlayingResult
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbService {
    // https://api.themoviedb.org/3/movie/now_playing?api_key=<<api_key>>&language=en-US&page=1
    @GET("movie/now_playing?api_key={apiKey}&language={language}&page={page}")
    suspend fun getNowPlaying(
        @Path("apiKey") apiKey: String,
        @Path("language") language: String,
        @Path("page") page: Int,
    ): NowPlayingResult

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
    @GET("movie/{movieId}?api_key={apiKey}&language={language}")
    suspend fun getMovie(
        @Path("apiKey") apiKey: String,
        @Path("language") language: String,
        @Path("movieId") movieId: Int,
    ):Movie
}