package com.example.orbit.di

import com.example.orbit.business.usecase.GetCountryListUseCase
import com.example.orbit.business.usecase.IGetCountryListUseCase
import com.example.orbit.data.repository.CountryRepository
import com.example.orbit.data.repository.ICountryRepository
import com.example.orbit.data.service.CountryService
import com.example.orbit.view.converter.CountryListConverter
import com.example.orbit.view.converter.ICountryListConverter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface CountryBindModule {

    @Singleton
    @Binds
    fun bindCountryRepository(impl: CountryRepository): ICountryRepository

    @Singleton
    @Binds
    fun bindGetCountryListUseCase(impl: GetCountryListUseCase): IGetCountryListUseCase

    @Singleton
    @Binds
    fun bindCountryListConverter(impl: CountryListConverter): ICountryListConverter

}

@InstallIn(SingletonComponent::class)
@Module
internal object CountryProvideModule {

    @Provides
    fun provideCountryService(retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)

}