package com.ap.creditreport.api

import com.ap.creditreport.CreditReportApiException
import com.ap.creditreport.models.CreditScoreReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * Implementation of [CreditReportRestApi] using Retrofit.
 */
class CreditReportRestApiImpl @Inject constructor() : CreditReportRestApi {

    companion object {
        const val BASE_URL = "https://android-interview.s3.eu-west-2.amazonaws.com/"
        const val GENERIC_FAILURE_MESSAGE = "Failed to fetch credit score report"
    }

    private val client: OkHttpClient = OkHttpClient.Builder().build()

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val api by lazy {
        service.create(CreditReportService::class.java)
    }


    override suspend fun fetchCreditScoreReport(): CreditScoreReport = withContext(Dispatchers.IO) {
        try {
            val response = api.getCreditReport()
            response.body() ?: throw CreditReportApiException(response.errorBody().toString())
        } catch (e: HttpException) {
            Timber.e(GENERIC_FAILURE_MESSAGE, e)
            throw CreditReportApiException(GENERIC_FAILURE_MESSAGE, e)
        } catch (e: IOException) {
            Timber.e(GENERIC_FAILURE_MESSAGE, e)
            throw CreditReportApiException(GENERIC_FAILURE_MESSAGE, e)
        }
    }
}