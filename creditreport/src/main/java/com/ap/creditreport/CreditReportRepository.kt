package com.ap.creditreport

/**
 * Interface providing means of accessing the credit report repository. This class allows us to expand the
 * interface into several APIs and services if necessary, whilst providing a single interface for
 * the ViewModel to interact with. This also allows for easier testing.
 */
interface CreditReportRepository {

    /**
     * Obtains the credit score of a user.
     */
    suspend fun getCreditReportScore(): Int
}