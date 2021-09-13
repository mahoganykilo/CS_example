package com.ap.csexample

import android.widget.ProgressBar
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.ap.csexample.models.UiState
import com.ap.csexample.ui.CreditScoreFragment
import com.ap.csexample.ui.CreditScoreFragment.Companion.PROGRESS_MAX
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CreditScoreFragmentTest {
//    @get:Rule
//    var activityScenarioRule:
    /**
     * View for Button that fetches credit score.
     */
    private val mButton = onView(withId(R.id.creditscorefetch_button))

    /**
     * View for Main textView for the Fragment
     */
    private val mMainTextView = onView(withId(R.id.textView_mainText))

    /**
     * View for doughnut progress bar.
     */
    private val mDoughnutProgressBar = onView(withId(R.id.progress_doughnut))

    /**
     * View for progress score textView
     */
    private val mProgressTextView = onView(withId(R.id.textView_progress))

    /**
     * Arbitrary credit score.
     */
    private val mScore = 100

    /**
     * GIVEN a user loads the credit score app
     * WHEN they land on the opening screen
     * THEN the welcome text and button for triggering the retrieval of a credit score
     * report are visible
     * AND the progress bar and score text are not visible.
     */
    @Test
    fun testFragmentInitialisesWith() {
        launchFragmentInContainer<CreditScoreFragment>()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val introText = context.getString(R.string.credit_score_intro_text)

        // Assert that the button and text are visible
        // (and the text matches the initial message).
        mMainTextView.check(matches(withText(introText)))
        mMainTextView.check(matches(isDisplayed()))
        mButton.check(matches(isDisplayed()))

        // Assert that the credit doughnut and text score are not displayed.
        mDoughnutProgressBar.check(matches(not(isDisplayed())))
        mProgressTextView.check(matches(not(isDisplayed())))
    }

    /**
     * GIVEN the user has successfully retrieved the
     * WHEN they land on the opening screen
     * THEN the welcome text and button for triggering the retrieval of a credit score
     * report are visible
     * AND the progress bar and score text are not visible.
     */
    @Test
    fun testSuccessfulState() {
        val scenario = launchFragmentInContainer<CreditScoreFragment>()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val successText = context.getString(R.string.credit_score_success_text)
        val scoreText =
            String.format(context.getString(R.string.credit_score_text), mScore, PROGRESS_MAX)

        var score = 0
        scenario.onFragment {
            it.renderState(UiState.SuccessState)
            it.handleScoreUpdate(mScore)
            // Find view by ID doesn't seem to get the progress bar here.
            score = it.activity?.findViewById<ProgressBar>(R.id.progress_doughnut)?.progress ?: 0
        }
        // Assert that the doughnut, progress text and main text are visible
        // (and the text matches the success message).
        mProgressTextView.check(matches(isDisplayed()))
        mProgressTextView.check(matches(withText(scoreText)))
        mMainTextView.check(matches(isDisplayed()))
        mMainTextView.check(matches(withText(successText)))
        mDoughnutProgressBar.check(matches(isDisplayed()))
        assert(score == mScore)

        // Assert that the button is not visible.
        mButton.check(matches(not(isDisplayed())))
    }

    @Test
    fun testErrorState() {
        val scenario = launchFragmentInContainer<CreditScoreFragment>()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val errorText = context.getString(R.string.credit_score_error_text)

        scenario.onFragment {
            it.renderState(UiState.ErrorState)
        }
        // Assert that the main textView and button are visible
        // (and the text matches the error message).
        mMainTextView.check(matches(withText(errorText)))
        mMainTextView.check(matches(isDisplayed()))
        mButton.check(matches(isDisplayed()))


        // Assert that the button, doughnut and score textView are not visible.
        mProgressTextView.check(matches(not(isDisplayed())))
        mDoughnutProgressBar.check(matches(not(isDisplayed())))

    }
}