package com.example.adrianwong.watchit.dagger.modules

import com.example.adrianwong.data.api.MovieApiService
import com.example.adrianwong.watchit.dagger.MovieApplicationScope
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule(private val baseUrl: String) {

    @Provides
    @MovieApplicationScope
    fun providesHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @MovieApplicationScope
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

        return client
    }

    @Provides
    @MovieApplicationScope
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit
    }

    @Provides
    @MovieApplicationScope
    fun providesMovieApiService(retrofit: Retrofit) : MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

}