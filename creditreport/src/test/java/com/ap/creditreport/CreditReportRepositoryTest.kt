package com.ap.creditreport

import com.ap.creditreport.api.CreditReportRestApi
import com.ap.creditreport.models.CreditScoreReport
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class CreditReportRepositoryTest {

    /**
     * Mocked API used in the data layer
     */
    private val mCreditReportRestApi = mockk<CreditReportRestApi>()

    /**
     * Class under test.
     */
    var mCreditReport = CreditReportRepositoryImpl(mCreditReportRestApi)

    companion object {
        const val CREDIT_SCORE = 100
    }

    @Before
    fun before() {
        val mockCreditReport = mockk<CreditScoreReport>()
        every { mockCreditReport.creditReportInfo.score } returns CREDIT_SCORE
        coEvery { mCreditReportRestApi.fetchCreditScoreReport() } returns mockCreditReport
    }

    /**
     * GIVEN a request for a credit report score is made
     * WHEN the request is successful
     * THEN the response is received containing the credit score
     */
    @Test
    fun testRetrievalOfCreditScore() = runBlocking {
        assert(mCreditReport.getCreditReportScore() == CREDIT_SCORE)
    }

    /**
     * GIVEN a request for a credit report score is made
     * WHEN the request fails
     * THEN the response is received containing the credit score
     */
    @Test()
    fun testFailureToRetrieveCreditScore(): Unit = runBlocking {
        coEvery { mCreditReportRestApi.fetchCreditScoreReport() } throws
                CreditReportApiException("")
        try {
            mCreditReport.getCreditReportScore()
            fail()
        } catch (e: Exception) {
            assert(e is CreditReportApiException)
        }
    }
}