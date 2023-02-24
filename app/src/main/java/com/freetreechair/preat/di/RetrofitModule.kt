package com.freetreechair.preat.di

import com.freetreechair.data.util.adapter.CustomCallAdapterFactory
import com.freetreechair.data.util.preferences.PreferencesDataSource
import com.freetreechair.preat.BuildConfig.DEBUG
import com.freetreechair.preat.BuildConfig.PREAT_SERVER_BASE_URL_DEBUG
import com.freetreechair.preat.BuildConfig.PREAT_SERVER_BASE_URL_RELEASE
import com.freetreechair.preat.annotations.PreatServer
import com.freetreechair.preat.interceptor.LoggingInterceptor
import com.freetreechair.preat.interceptor.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val AUTHORIZATION = "Authorization"
    private const val BEARER ="Bearer "

    /**
     * @author onseok
     *
     * 서버로부터 response body 자체가 null인 경우,
     * 즉, 비어있는(length=0)인 response를 받았을 경우,
     * 앱이 터지지 않도록,
     * 이에 대한 예외를 처리하는 역할을 합니다.
     */
    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) =
            object :
                Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    @PreatServer
    fun providesPreatInterceptor(preferencesDataSourceImpl: PreferencesDataSource): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(
                            AUTHORIZATION,
                            BEARER + preferencesDataSourceImpl.getAccessToken()
                        )
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    @PreatServer
    fun providesPreatOkHttpClient(
        networkStatusInterceptor: NetworkStatusInterceptor,
        @PreatServer authenticationInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(authenticationInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @PreatServer
    fun providesPreatRetrofit(@PreatServer okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(if (DEBUG) PREAT_SERVER_BASE_URL_DEBUG else PREAT_SERVER_BASE_URL_RELEASE)
            .client(okHttpClient)
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
