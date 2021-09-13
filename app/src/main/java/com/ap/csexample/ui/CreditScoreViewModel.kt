package com.ap.csexample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ap.creditreport.CreditReportApiException
import com.ap.creditreport.CreditReportRepository
import com.ap.csexample.models.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for [CreditScoreFragment].
 */
@HiltViewModel
class CreditScoreViewModel @Inject constructor(
    private val mCreditScoreReportRepository: CreditReportRepository
) : ViewModel() {

    private var _creditReportScore: MutableLiveData<Int> = MutableLiveData(null)
    val creditReportScore: LiveData<Int> = _creditReportScore

    val uiState: MutableLiveData<UiState> = MutableLiveData(UiState.InitialState)


    /**
     * Fetch the credit score using the API, then update the UI state to reflect that a success has
     * occurred and then update the [LiveData] credit score.
     * If the request to the endpoint fails, then update the UI state to reflect that a failure has
     * occurred.
     */
    fun fetchCreditScore() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val creditScoreReport = mCreditScoreReportRepository.getCreditReportScore()
                uiState.postValue(UiState.SuccessState)
                updateCreditScoreReport(creditScoreReport)
            } catch (e: CreditReportApiException) {
                uiState.postValue(UiState.ErrorState)
            }
        }
    }


    /**
     * Method for posting Credit Scores to [LiveData] from a background thread.
     */
    private fun updateCreditScoreReport(creditReportScore: Int) {
        _creditReportScore.postValue(creditReportScore)
    }
}