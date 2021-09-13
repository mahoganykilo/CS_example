package com.ap.csexample.di

import com.ap.csexample.ui.CreditScoreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Class that defines the [Module] for the [CreditScoreFragment].
 */
@Module
abstract class CreditScoreModule {
    @ContributesAndroidInjector
    internal abstract fun creditScoreFragment(): CreditScoreFragment
}