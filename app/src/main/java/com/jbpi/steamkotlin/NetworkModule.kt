package com.jbpi.steamkotlin

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jbpi.steamkotlin.api.MyJsonDeserializer
import com.jbpi.steamkotlin.api.SteamWebApiService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun providesSteamWebApiService(retrofit: Retrofit): SteamWebApiService {

        return retrofit.create(SteamWebApiService::class.java)
    }

    @Provides @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder()
                .baseUrl("http://api.steampowered.com/ISteamUserStats/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    internal fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    internal fun providesGson(): Gson {

        return GsonBuilder()
                .registerTypeAdapter(ArrayAchievementsData::class.java, MyJsonDeserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create()
    }
}