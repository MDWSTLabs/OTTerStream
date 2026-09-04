package com.mdwst.otterstream.di

import android.content.Context
import com.mdwst.otterstream.data.api.AllDebridApi
import com.mdwst.otterstream.data.api.PremiumizeApi
import com.mdwst.otterstream.data.api.RealDebridApi
import com.mdwst.otterstream.data.api.StremioAddonApi
import com.mdwst.otterstream.data.api.TmdbApi
import com.mdwst.otterstream.data.db.OtterStreamDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideStremioAddonApi(
        json: Json,
        okHttpClient: OkHttpClient
    ): StremioAddonApi {
        return Retrofit.Builder()
            .baseUrl("http://localhost/") // Dynamic base URL
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(StremioAddonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTmdbApi(
        json: Json,
        okHttpClient: OkHttpClient
    ): TmdbApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(TmdbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRealDebridApi(
        json: Json,
        okHttpClient: OkHttpClient
    ): RealDebridApi {
        return Retrofit.Builder()
            .baseUrl("https://api.real-debrid.com/rest/1.0/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RealDebridApi::class.java)
    }

    @Provides
    @Singleton
    fun providePremiumizeApi(
        json: Json,
        okHttpClient: OkHttpClient
    ): PremiumizeApi {
        return Retrofit.Builder()
            .baseUrl("https://www.premiumize.me/api/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(PremiumizeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAllDebridApi(
        json: Json,
        okHttpClient: OkHttpClient
    ): AllDebridApi {
        return Retrofit.Builder()
            .baseUrl("https://api.alldebrid.com/v4/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AllDebridApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideOtterStreamDatabase(
        @ApplicationContext context: Context
    ): OtterStreamDatabase = OtterStreamDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideAddonDao(db: OtterStreamDatabase) = db.addonDao()

    @Provides
    @Singleton
    fun provideAddonCatalogDao(db: OtterStreamDatabase) = db.addonCatalogDao()

    @Provides
    @Singleton
    fun provideAddonMetadataDao(db: OtterStreamDatabase) = db.addonMetadataDao()

    @Provides
    @Singleton
    fun provideAddonStreamDao(db: OtterStreamDatabase) = db.addonStreamDao()

    @Provides
    @Singleton
    fun provideWatchlistDao(db: OtterStreamDatabase) = db.watchlistDao()

    @Provides
    @Singleton
    fun provideContinueWatchingDao(db: OtterStreamDatabase) = db.continueWatchingDao()
}

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideAddonEngine(
        addonApi: StremioAddonApi,
        addonDao: com.mdwst.otterstream.data.db.AddonDao,
        catalogDao: com.mdwst.otterstream.data.db.AddonCatalogDao,
        metadataDao: com.mdwst.otterstream.data.db.AddonMetadataDao,
        streamDao: com.mdwst.otterstream.data.db.AddonStreamDao,
        json: Json
    ) = com.mdwst.otterstream.domain.service.AddonEngine(
        addonApi, addonDao, catalogDao, metadataDao, streamDao, json
    )
}
