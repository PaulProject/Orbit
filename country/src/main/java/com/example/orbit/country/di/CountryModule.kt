package com.example.orbit.country.di

import com.example.orbit.country.business.usecase.GetCountryDetailUseCase
import com.example.orbit.country.business.usecase.GetCountryListUseCase
import com.example.orbit.country.business.usecase.IGetCountryDetailUseCase
import com.example.orbit.country.business.usecase.IGetCountryListUseCase
import com.example.orbit.country.data.repository.CountryRepository
import com.example.orbit.country.data.repository.ICountryRepository
import com.example.orbit.country.data.service.CountryService
import com.example.orbit.country.view.detail.converter.CountryDetailConverter
import com.example.orbit.country.view.detail.converter.ICountryDetailConverter
import com.example.orbit.country.view.list.converter.CountryListConverter
import com.example.orbit.country.view.list.converter.ICountryListConverter
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
    fun bindGetCountryDetailUseCase(impl: GetCountryDetailUseCase): IGetCountryDetailUseCase

    @Singleton
    @Binds
    fun bindCountryListConverter(impl: CountryListConverter): ICountryListConverter

    @Singleton
    @Binds
    fun bindCountryDetailConverter(impl: CountryDetailConverter): ICountryDetailConverter

}

@InstallIn(SingletonComponent::class)
@Module
internal object CountryProvideModule {

    @Provides
    fun provideCountryService(retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)

}