package com.alertbook.alertbook.module

import android.content.Context
import com.alertbook.alertbook.data.DataStoreRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    @Singleton
    fun provideDataStoreRepo(
        @ApplicationContext context: Context
    ) = DataStoreRepo(context = context)
}