package az.azerconnect.data.api.client

import az.azerconnect.data.BuildConfig
import az.azerconnect.data.api.interceptors.AuthorizationInterceptor
import az.azerconnect.data.api.interceptors.HttpLoggingInterceptor
import az.azerconnect.data.api.interceptors.UnAuthorizedInterceptor
import az.azerconnect.data.api.services.AuthApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object BakcellApiClient {
    fun provideApi(): AuthApiService {
        return getRetrofit("${BuildConfig.BASE_URL}${version()}").create(AuthApiService::class.java)
    }


    private fun getRetrofit(baseUrl: String): Retrofit {
        val client = OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(UnAuthorizedInterceptor())
            .addInterceptor(AuthorizationInterceptor())
            .addNetworkInterceptor(HttpLoggingInterceptor.getInterceptor())
            .build()

        val gson = GsonBuilder()
            .setPrettyPrinting()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    private fun version() = "v2/"
}