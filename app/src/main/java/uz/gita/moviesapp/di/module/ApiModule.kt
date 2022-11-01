package uz.gita.moviesapp.di.module

import android.content.Context
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.moviesapp.BuildConfig.BASE_URL
import uz.gita.moviesapp.data.sources.remote.api.MoviesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    //   OkHTTP Object
    @[Provides Singleton]
    fun okHttpClientObject(
        @ApplicationContext context: Context
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(PlutoInterceptor())
        .build()

    //Retrofit Object
    @[Provides Singleton]
    fun getRetrofitObject(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    //Api service method
    @[Provides Singleton]
    fun getMoviesApi(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)
}