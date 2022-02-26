package com.prasoon.themoviedatabase.di

import com.prasoon.themoviedatabase.common.Constants
import com.prasoon.themoviedatabase.data.remote.MovieDatabaseApi
import com.prasoon.themoviedatabase.data.repository.MovieRepositoryImpl
import com.prasoon.themoviedatabase.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // 1. Create a Retrofit instance
    @Provides
    @Singleton
    fun provideMovieDatabaseApi(): MovieDatabaseApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDatabaseApi::class.java)
    }

    // 2. Create the Retrofit api
    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieDatabaseApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}