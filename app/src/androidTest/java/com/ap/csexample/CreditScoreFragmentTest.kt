package com.ap.csexample

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.ap.csexample.ui.CreditScoreFragment
import com.ap.csexample.ui.CreditScoreFragment.Companion.PROGRESS_MAX
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for the [CreditScoreFragment]
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class CreditScoreFragmentTest {
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
    private val mScore = 514

    /**
     * GIVEN a user loads the credit score app
     * WHEN they land on the opening screen
     * THEN the welcome text and button for triggering the retrieval of a credit score
     * report are visible
     * AND any components related to displaying their credit score are available.
     */
    @Test
    fun testFragmentInitialisesWithCreditScoreRequestAvailable() {
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
     * GIVEN the user wants to retrieve their credit score
     * WHEN the user interacts with a mechanism to retrieve their credit score
     * THEN their credit score is displayed to them.
     */
    @Test
    fun testCreditScoreIsObtainedSuccessfully() {
        launchFragmentInContainer<CreditScoreFragment>()
        mButton.perform(click())
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val successText = context.getString(R.string.credit_score_success_text)
        val scoreText =
            String.format(context.getString(R.string.credit_score_text), mScore, PROGRESS_MAX)
        // Assert that the doughnut, progress text and main text are visible
        // (and the text matches the success message).
        mProgressTextView.check(matches(isDisplayed()))
        mProgressTextView.check(matches(withText(scoreText)))
        mMainTextView.check(matches(isDisplayed()))
        mMainTextView.check(matches(withText(successText)))
        mDoughnutProgressBar.check(matches(isDisplayed()))

        // Assert that the button is not visible.
        mButton.check(matches(not(isDisplayed())))
    }

    /**
     * GIVEN the user wants to retrieve their credit score but their is no internet
     * WHEN the user interacts with a mechanism to retrieve their credit score
     * THEN their credit score is not retrieved and shown to them
     * AND they are informed that their attempt to fetch their credit score failed.
     */
    @Test
    fun testScreenShowsErrorStateWhenCreditScoreRetrievalFails() {
        InstrumentationRegistry.getInstrumentation().uiAutomation
            .executeShellCommand("svc wifi disable")
        launchFragmentInContainer<CreditScoreFragment>()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val errorText = context.getString(R.string.credit_score_error_text)

        mButton.perform(click())
        // Assert that the main textView and button are visible
        // (and the text matches the error message).
        mMainTextView.check(matches(withText(errorText)))
        mMainTextView.check(matches(isDisplayed()))
        mButton.check(matches(isDisplayed()))

        // Assert that the button, doughnut and score textView are not visible.
        mProgressTextView.check(matches(not(isDisplayed())))
        mDoughnutProgressBar.check(matches(not(isDisplayed())))
    }

    @After
    fun ensureWifiEnabled() {
        InstrumentationRegistry.getInstrumentation().uiAutomation
            .executeShellCommand("svc wifi enable")
    }
}