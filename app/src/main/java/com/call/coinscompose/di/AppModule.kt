package com.call.coinscompose.di

import com.call.coinscompose.data.remote.CoinsApi
import com.call.coinscompose.data.remote.repository.CoinsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinsApi(moshi: Moshi): CoinsApi {
        return Retrofit.Builder()
            .baseUrl("http://carlosapi.somee.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CoinsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinsRepository(coinsApi: CoinsApi): CoinsRepository {
        return CoinsRepository(coinsApi)
    }
}