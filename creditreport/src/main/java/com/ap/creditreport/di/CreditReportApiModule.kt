package com.ap.creditreport.di

import com.ap.creditreport.api.CreditReportRestApi
import com.ap.creditreport.api.CreditReportRestApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Provides a DI [Module] for the [CreditReportRestApi].
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CreditReportApiModule {

    /**
     * Binding method for implementation of [CreditReportRestApi].
     * @param impl The implementation to be bound.
     */
    @Binds
    abstract fun provideCreditReportRestApi(impl: CreditReportRestApiImpl): CreditReportRestApi
}