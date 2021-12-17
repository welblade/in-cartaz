package com.github.welblade.incartaz.data.model

data class NowPlayingResult (
    val dates: Dates,
    val page: Long,
    val results: List<Result>,
    val totalPages: Long,
    val totalResults: Long
)

data class Result (
    val adult: Boolean,
    val backdropPath: String,
    val genreIDS: List<Long>,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)

data class Dates (
    val maximum: String,
    val minimum: String
)