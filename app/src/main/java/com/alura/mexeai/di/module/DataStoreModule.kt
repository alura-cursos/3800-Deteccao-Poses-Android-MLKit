package com.alura.mexeai.di.module

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.alura.mexeai.dataStore.UserPreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private const val MEXEAI_DATASTORE = "mexeai_datastore"

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStoreUserPreferences(@ApplicationContext context: Context): UserPreferencesDataStore {
        val dataStore = PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(
                    MEXEAI_DATASTORE
                )
            }
        )
        return UserPreferencesDataStore(dataStore)
    }
}

