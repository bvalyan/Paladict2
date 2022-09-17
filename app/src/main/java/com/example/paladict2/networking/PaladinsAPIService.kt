package com.example.paladict2.networking

import com.example.paladict2.Constants
import com.example.paladict2.model.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface PaladinsAPIService {

    @GET("createsessionjson/{dev_id}/{signature}/{date}")
    fun createAPISession(
        @Path("dev_id") devID: String,
        @Path("signature") signature: String,
        @Path("date") date: String
    ): Deferred<Session>

    @GET("getchampionsjson/{dev_id}/{signature}/{session}/{date}/{langCode}")
    fun getChampions(
        @Path("dev_id") devID: String,
        @Path("signature") signature: String,
        @Path("date") date: String,
        @Path("session") session: String,
        @Path("langCode") langCode: String
    ): Deferred<MutableList<Champion>>

    @GET("getitemsjson/{dev_id}/{signature}/{session}/{date}/{langCode}")
    fun getItems(
        @Path("dev_id") devID: String,
        @Path("signature") signature: String,
        @Path("date") date: String,
        @Path("session") session: String,
        @Path("langCode") langCode: String
    ): Deferred<MutableList<Item>>

    @GET("getplayerjson/{dev_id}/{signature}/{session}/{date}/{player}")
    fun getplayer(
        @Path("dev_id") devID: String?,
        @Path("signature") signature: String?,
        @Path("date") date: String?,
        @Path("session") session: String?,
        @Path("player") player: String?
    ): Deferred<MutableList<Player>>

    @GET("searchplayersjson/{dev_id}/{signature}/{session}/{date}/{player}")
    fun searchPlayers(
        @Path("dev_id") devID: String?,
        @Path("signature") signature: String?,
        @Path("date") date: String?,
        @Path("session") session: String?,
        @Path("player") player: String?
    ): Deferred<MutableList<Player>>

    @GET("getmatchhistoryjson/{dev_id}/{signature}/{session}/{date}/{player}")
    fun getMatchHistory(
        @Path("dev_id") devID: String,
        @Path("signature") signature: String,
        @Path("date") date: String,
        @Path("session") session: String,
        @Path("player") player: String
    ): Deferred<MutableList<Match>>

    @GET("getfriendsjson/{dev_id}/{signature}/{session}/{date}/{player}")
    fun getFriends(
        @Path("dev_id") devID: String?,
        @Path("signature") signature: String?,
        @Path("date") date: String?,
        @Path("session") session: String?,
        @Path("player") player: String?
    ): Deferred<MutableList<Player>>

    companion object {
        fun createCoreService(): PaladinsAPIService {
            val logging = HttpLoggingInterceptor()
// set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.PALADINS_API_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(PaladinsAPIService::class.java)
        }
    }
}
