package co.proexe.data.time.di

import co.proexe.data.time.repository.TimeRemoteRepository
import co.proexe.data.time.repository.TimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TimeModule {
    @Binds
    @Singleton
    abstract fun bindTimeRepository(timeRemoteRepository: TimeRemoteRepository): TimeRepository
}