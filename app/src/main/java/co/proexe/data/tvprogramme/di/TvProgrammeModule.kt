package co.proexe.data.tvprogramme.di

import co.proexe.data.tvprogramme.repository.TvProgrammeRepository
import co.proexe.data.tvprogramme.repository.TvProgrammeRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TvProgrammeModule {
    @Binds
    @Singleton
    abstract fun bindMainRepository(mainRepositoryRemoteRepository: TvProgrammeRemoteRepository): TvProgrammeRepository
}