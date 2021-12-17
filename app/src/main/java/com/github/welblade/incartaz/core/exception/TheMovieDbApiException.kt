package com.github.welblade.incartaz.core.exception

import com.github.welblade.incartaz.data.model.ErrorResponse

class TheMovieDbApiException(
    private val errorResponse: ErrorResponse
    ): Throwable(errorResponse.statusMessage) {
        override val message: String
        get() = errorResponse.statusMessage
        val statusCode: Int
        get() = errorResponse.statusCode
}
