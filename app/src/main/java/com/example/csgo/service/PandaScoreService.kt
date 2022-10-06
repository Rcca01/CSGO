package com.example.csgo.service

import com.example.csgo.model.Matches
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PandaScoreService {
    @GET("matches")
    fun getListMatches(): Call<List<Matches>>

    companion object {
        var retrofitService: PandaScoreService? = null

        private val client =  OkHttpClient.Builder()
            .addInterceptor(OAuthInterceptor("Bearer", "A5VFk06kPvUrbmWRk4fW3j1-xNk71dasXPURrg8q0pnQwkdWtwY"))
            .build()

        fun getInstance() : PandaScoreService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://api.pandascore.co/csgo/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(PandaScoreService::class.java)
            }
            return retrofitService!!
        }
    }
}

class OAuthInterceptor(private val tokenType: String, private val acceessToken: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$tokenType $acceessToken").build()

        return chain.proceed(request)
    }
}