package com.ap.creditreport.di

import com.ap.creditreport.CreditReportRepository
import com.ap.creditreport.CreditReportRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * Provides a DI [Module] for the [CreditReportRepository].
 */
@Module
abstract class CreditReportRepositoryModule {

    /**
     * Binding method for implementation of [CreditReportRepository].
     * @param impl The implementation to be bound.
     */
    @Binds
    abstract fun provideCreditReportRepository(impl: CreditReportRepositoryImpl): CreditReportRepository
}