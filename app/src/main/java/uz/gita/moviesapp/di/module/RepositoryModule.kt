package uz.gita.moviesapp.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.moviesapp.data.repository.MoviesRepository
import uz.gita.moviesapp.data.repository.impl.MoviesRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindsMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository
}