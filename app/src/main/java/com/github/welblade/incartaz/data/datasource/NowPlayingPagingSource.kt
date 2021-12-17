package com.github.welblade.incartaz.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.welblade.incartaz.BuildConfig
import com.github.welblade.incartaz.data.model.Result
import com.github.welblade.incartaz.data.service.TheMovieDbService
import retrofit2.HttpException
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1L
const val NETWORK_PAGE_SIZE = 25

class NowPlayingPagingSource(
    private val service: TheMovieDbService
) : PagingSource<Long, Result>() {
    private val apikey = BuildConfig.theMovieDbApiKey
    private val language = "pt-BR"

    override fun getRefreshKey(state: PagingState<Long, Result>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Result> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = service.getNowPlaying(
                apiKey = apikey,
                language = language,
                page = pageIndex
            )
            val movies = response.results
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}