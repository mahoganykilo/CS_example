package com.ap.csexample.di

import dagger.Module

/**
 * Object that defines the application module for Dagger.
 */
@Module(includes = [ApplicationModuleBinds::class])
object ApplicationModule

/**
 * Class that defines what the application binds to.
 */
@Module
abstract class ApplicationModuleBinds