package com.jcdelacueva.bluefetchassignment2.data

import com.jcdelacueva.bluefetchassignment2.data.model.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

val apiClient: Retrofit by lazy {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
//        .addInterceptor(Interceptor { chain ->
//            val original = chain.request()
////            val request = original.
//            val request = original.newBuilder()
//                .header("Authorization", "-NHDiVdBFa9f2iLAKz7A")
//                .method(original.method, original.body)
//                .build()
//
//            chain.proceed(request)
//        })
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    Retrofit.Builder()
        .baseUrl("https://us-central1-bluefletch-learning-assignment.cloudfunctions.net")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}

val apiInterface: Api by lazy {
    apiClient.create(Api::class.java)
}

interface Api {

    @Headers("Content-Type: application/json")
    @POST("/account/create")
    suspend fun register(@Body registrationInfo: RegistrationInfo): TokenResponse

    @Headers("Content-Type: application/json")
    @POST("/account/login")
    suspend fun login(@Body loginData: LoginInfo): TokenResponse

    @GET("/feed")
    suspend fun getFeed(@Header("Authorization") token: String, @Query("limit") limit: Int): List<Feed>
}