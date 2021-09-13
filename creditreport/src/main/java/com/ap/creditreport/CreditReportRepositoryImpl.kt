package com.ap.creditreport

import com.ap.creditreport.api.CreditReportRestApi
import com.ap.creditreport.models.CreditScoreReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of the [CreditReportRepository] interface.
 */
class CreditReportRepositoryImpl @Inject constructor(
    private val mApi: CreditReportRestApi
) : CreditReportRepository {


    /**
     * Obtains the full [CreditScoreReport] for a user.
     * @return The full [CreditScoreReport] report.
     */
    private suspend fun getCreditReport(): CreditScoreReport = withContext(Dispatchers.IO) {
        mApi.fetchCreditScoreReport()
    }

    override suspend fun getCreditReportScore(): Int = withContext(Dispatchers.IO) {
        val creditReport = getCreditReport()
        creditReport.creditReportInfo.score
    }
}