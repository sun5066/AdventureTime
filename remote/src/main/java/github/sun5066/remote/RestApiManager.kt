package github.sun5066.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RestApiManager {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun makeAdventureTimeService(): AdventureTimeService {
        val retrofit = makeRetrofit(makeOkHttpClient())

        return retrofit.create(AdventureTimeService::class.java)
    }

    private fun makeRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://adventure-time-api.herokuapp.com/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    private fun makeOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor(true))
            .build()

    private fun makeLoggingInterceptor(debug: Boolean) =
        HttpLoggingInterceptor().apply {
            if (debug)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }


}