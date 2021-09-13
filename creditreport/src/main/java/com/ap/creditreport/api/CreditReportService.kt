package com.ap.creditreport.api

import com.ap.creditreport.models.CreditScoreReport
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface for Credit Reports from the provided endpoints.
 */
internal interface CreditReportService {

    companion object {
        const val CREDIT_REPORT_PATH = "endpoint.json"
    }

    /**
     * GET method for retrieving the users credit score.
     */
    @GET(CREDIT_REPORT_PATH)
    suspend fun getCreditReport(): Response<CreditScoreReport>
}