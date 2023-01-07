package comsmilias.example.databaseanalyzer.di

import comsmilias.example.databaseanalyzer.data.remote.repository.FirebaseRepositoryImpl
import comsmilias.example.databaseanalyzer.domain.repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseRepository(): FirebaseRepository {
        return FirebaseRepositoryImpl()
    }
}