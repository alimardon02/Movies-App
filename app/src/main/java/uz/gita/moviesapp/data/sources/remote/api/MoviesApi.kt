package uz.gita.moviesapp.data.sources.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.moviesapp.data.sources.common.models.MoviesData
import uz.gita.moviesapp.utils.ResultData

/**
 *Api dan malumotlar olish uchun HTTP so"rov yozish uchun
 */
interface MoviesApi {

    /**
     * filmlar listini olish uchun page orqali
     */
    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MoviesData>

    /**
     * flimlar listidan film izlash uchun funksiya
     */
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<MoviesData>
}