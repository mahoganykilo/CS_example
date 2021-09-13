package com.ap.csexample.di

import android.content.Context
import com.ap.creditreport.di.CreditReportApiModule
import com.ap.creditreport.di.CreditReportRepositoryModule
import com.ap.csexample.MainActivity
import com.ap.csexample.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Interface that defines the dagger grap for the application.
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        CreditScoreModule::class,
        CreditReportApiModule::class,
        CreditReportRepositoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MainApplication> {

    /**
     * Interface that ties the application context to the application component.
     */
    @Component.Factory
    interface Factory {
        /**
         * Ties the application context to the application component on creation.
         * @param applicationContext Context to bind to the dagger graph
         */
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    /**
     * Permits Dagger to inject into the [MainActivity] class.
     * @param activity The activity being injected into.
     */
    fun inject(activity: MainActivity)
}