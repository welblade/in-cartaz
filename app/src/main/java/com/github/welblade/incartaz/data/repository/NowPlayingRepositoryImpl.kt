package com.github.welblade.incartaz.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.welblade.incartaz.data.datasource.NowPlayingPagingSource
import com.github.welblade.incartaz.data.model.Result
import com.github.welblade.incartaz.data.service.TheMovieDbService
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 25

class NowPlayingRepositoryImpl(
    private val service: TheMovieDbService
): NowPlayingRepository {
    override suspend fun getNowPlaying(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NowPlayingPagingSource(service)
            }
        ).flow
    }
}