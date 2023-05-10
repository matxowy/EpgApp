package co.proexe.data.main.di

import co.proexe.data.main.repository.MainRepository
import co.proexe.data.main.repository.MainRepositoryRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {
    @Binds
    @Singleton
    abstract fun bindMainRepository(mainRepositoryRemoteRepository: MainRepositoryRemoteRepository): MainRepository
}