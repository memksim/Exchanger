package com.memksim.exchanger.di

import android.content.Context
import androidx.room.Room
import com.memksim.exchanger.data.local.CurrencyDao
import com.memksim.exchanger.data.local.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalStoreModule {


    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): CurrencyDatabase =
        Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java,
            "CurrencyDb"
        ).build()


    @Provides
    @Singleton
    fun provideDao(
        db: CurrencyDatabase
    ): CurrencyDao = db.getDao()

}