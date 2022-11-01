package uz.gita.moviesapp.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.moviesapp.domain.usecase.MoviesUseCase
import uz.gita.moviesapp.domain.usecase.impl.MoviesUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindsMoviesUseCase(impl: MoviesUseCaseImpl): MoviesUseCase
}