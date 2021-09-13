package com.ap.creditreport.api

import com.ap.creditreport.models.CreditScoreReport

/**
 * API service for requesting a credit score report for a nominated user.
 */
interface CreditReportRestApi {

    /**
     * Requests a [CreditScoreReport] from an external unauthenticated endpoint.
     */
    suspend fun fetchCreditScoreReport(): CreditScoreReport
}