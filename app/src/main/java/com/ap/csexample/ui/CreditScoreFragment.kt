package com.ap.csexample.ui

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ap.csexample.R
import com.ap.csexample.animations.ProgressBarAnimation
import com.ap.csexample.animations.circularReveal
import com.ap.csexample.databinding.CreditscoreFragmentBinding
import com.ap.csexample.di.ViewModelFactory
import com.ap.csexample.models.UiState
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * Fragment for displaying the Credit Score.
 */
@Suppress("TooManyFunctions")
class CreditScoreFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CreditScoreViewModel>

    // In a more complicated application (where classes need to be injected) we would provide the
    // viewModel via a factory that injects it automatically.
    private val mViewModel by viewModels<CreditScoreViewModel> { viewModelFactory }
    private lateinit var mBinding: CreditscoreFragmentBinding

    companion object {
        const val PROGRESS_START = 0
        const val PROGRESS_MAX = 700
        const val RELATIVE_SCORE_SIZE = 3.8f
        const val MIDWAY_POINT = 0.5

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = CreditscoreFragmentBinding.inflate(inflater)
        setupButtonClickListener()
        observeCreditReportScore()
        observeUiState()

        return mBinding.root
    }

    /**
     * When there are a small number of UI changes to be made, using a when statement to handle the
     * different cases is quick and easy. As the complexity grows (in either the number of UI states
     * to handle or the number of UI components to manage state for) I would move to creating
     * separate layouts for each of these. Depending on the similarity of any potential async tasks
     * being performed in the application, these layouts could be made to be generic for re-use, and
     * can be made visible depending on the UI state currently in play.
     */
    private fun observeUiState() {
        mViewModel.uiState.observe(viewLifecycleOwner, { uiState ->
            renderState(uiState)
        })
    }

    /**
     * Determine the current UI state and then perform the rendering actions related to that state.
     * @param uiState The [UiState] to render.
     */
    private fun renderState(uiState: UiState) {
        when (uiState) {
            is UiState.InitialState -> {
                /**No-Op*/
            }
            is UiState.LoadingState -> onLoading()
            is UiState.ErrorState -> onError()
            is UiState.SuccessState -> onSuccess()
        }
    }

    /**
     * Handle UI changes when an asynchronous task is begun.
     */
    private fun onLoading() {
        mBinding.apply {
            textViewMainText.text = requireContext().getString(R.string.credit_score_loading_text)
            creditscorefetchButton.circularReveal(false)
            groupProgressDoughnut.circularReveal(false)
            textViewProgress.circularReveal(false)
        }
    }

    /**
     * Handle UI changes when an asynchronous task succeeds.
     */
    private fun onSuccess() {
        mBinding.apply {
            textViewMainText.text = requireContext().getString(R.string.credit_score_success_text)
            creditscorefetchButton.circularReveal(false)
            textViewProgress.circularReveal(true)
            groupProgressDoughnut.circularReveal(true)
        }
    }

    /**
     * Handle UI changes when an asynchronous task fails.
     */
    private fun onError() {
        mBinding.apply {
            textViewMainText.text = requireContext().getString(R.string.credit_score_error_text)
            creditscorefetchButton.circularReveal(true)
            groupProgressDoughnut.circularReveal(false)
            textViewProgress.circularReveal(false)
        }
    }

    /**
     * Set up observer for updated credit report.
     */
    private fun observeCreditReportScore() {
        mViewModel.creditReportScore.observe(viewLifecycleOwner, { creditScoreReport ->
            if (creditScoreReport != null) handleScoreUpdate(creditScoreReport)
        })
    }

    /**
     * Set up listener for credit score fetch button.
     */
    private fun setupButtonClickListener() {
        with(mBinding) {
            creditscorefetchButton.apply {
                setOnClickListener {
                    mViewModel.fetchCreditScore()
                }
            }
        }
    }

    /**
     * Set the credit score and animate the progress bar.
     * @param creditReportScore The score to be set.
     */
    private fun handleScoreUpdate(creditReportScore: Int) {

        val animation = ProgressBarAnimation(
            mBinding.progressDoughnut,
            PROGRESS_START,
            creditReportScore
        )
        mBinding.progressDoughnut.max = PROGRESS_MAX
        mBinding.textViewProgress.text = generateScoreString(creditReportScore)
        mBinding.progressDoughnut.startAnimation(animation)
    }

    /**
     * Perform the formatting on the score string to increase the score size and programmatically
     * set the colour to match the transition.
     * @param score The credit score to be formatted within the string.
     */
    private fun generateScoreString(score: Int): SpannableString {
        val stringifiedScore = score.toString()
        val scoreString =
            String.format(
                requireContext().getString(R.string.credit_score_text),
                score,
                PROGRESS_MAX
            )
        val wordPosition = scoreString.indexOf(stringifiedScore)
        val spannedString = SpannableString(scoreString)
        spannedString.setSpan(
            RelativeSizeSpan(RELATIVE_SCORE_SIZE),
            wordPosition,
            wordPosition + stringifiedScore.length,
            0
        )
        spannedString.setSpan(
            ForegroundColorSpan(calculateTextColour(score)),
            wordPosition,
            wordPosition + stringifiedScore.length,
            0
        )
        return spannedString
    }

    /**
     * Uses an RGBA evaluator to calculate the progress of the sweep gradient, and ensure that the
     * text colour matches.
     * @param score The credit score, used to calculate the gradient point reached on the progress
     * bar.
     */
    private fun calculateTextColour(score: Int): Int {
        val startColour = resources.getColor(R.color.orange_500, null)
        val middleColour = resources.getColor(R.color.yellow_500, null)
        val endColour = resources.getColor(R.color.green_500, null)
        val ratio = score.toFloat() / PROGRESS_MAX
        val evaluator = ArgbEvaluator()
        val textColour = if (ratio > MIDWAY_POINT) {
            evaluator.evaluate(ratio, middleColour, endColour)
        } else {
            evaluator.evaluate(ratio, startColour, middleColour)
        }
        return textColour as Int
    }

}