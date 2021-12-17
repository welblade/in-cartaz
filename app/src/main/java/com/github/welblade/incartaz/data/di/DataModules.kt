package com.github.welblade.incartaz.data.di

import android.util.Log
import com.github.welblade.incartaz.data.repository.MovieRepository
import com.github.welblade.incartaz.data.repository.MovieRepositoryImpl
import com.github.welblade.incartaz.data.repository.NowPlayingRepository
import com.github.welblade.incartaz.data.repository.NowPlayingRepositoryImpl
import com.github.welblade.incartaz.data.service.TheMovieDbService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModules {
    private const val HTTP_TAG = "OkHttp"
    fun load(){
        loadKoinModules(networkModule() + repositoryModule())
    }

    private fun networkModule(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.d(HTTP_TAG, ": $it")
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single{
                createService<TheMovieDbService>(get(), get())
            }
        }
    }

    private fun repositoryModule(): Module {
        return module {
            single<NowPlayingRepository> { NowPlayingRepositoryImpl(get())}
            single<MovieRepository> { MovieRepositoryImpl(get()) }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }
}