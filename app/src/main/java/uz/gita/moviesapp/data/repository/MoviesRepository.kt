package uz.gita.moviesapp.data.repository

import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import uz.gita.moviesapp.data.sources.common.models.MoviesData
import uz.gita.moviesapp.data.sources.common.models.Results


interface MoviesRepository {

    /**
     * barcha filmlar listi
     */
    suspend fun getAllMovies(apiKey: String, page: Int): Response<MoviesData>

    /**
     * qidiruv orqali qaytgan filmlar listi uchun funksiya
     */
    suspend fun getSearchMovies(apiKey: String, query: String, page: Int): Response<MoviesData>
}