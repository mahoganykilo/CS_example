package com.ap.csexample.animations

import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import androidx.core.animation.doOnEnd
import kotlin.math.hypot

/**
 * Function for adding a soft, circular reveal on components to make their appearance more
 * visually appealing.
 */
fun View.circularReveal(show: Boolean) {
    val cx = width / 2
    val cy = height / 2
    val radius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
    if (show && visibility != View.VISIBLE) {
        val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, 0f, radius)
        visibility = View.VISIBLE
        anim.start()
    } else if (!show && (visibility == View.VISIBLE)) {
        val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, radius, 0f)
        anim.doOnEnd { visibility = View.INVISIBLE }
        anim.start()
    }
}

/**
 * Animate the progress bar when progress is set.
 * @param progressBar The progress bar to apply the animation to.
 * @param from The starting point of the progress animation
 * @param to The end point of the progress animation
 */
class ProgressBarAnimation(
    private val progressBar: ProgressBar,
    private val from: Int,
    private val to: Int
) : Animation() {

    companion object {
        const val ANIMATION_DURATION = 1500L
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val updateValue = from + (to - from) * interpolatedTime
        this.duration = ANIMATION_DURATION

        progressBar.progress = updateValue.toInt()
        progressBar.secondaryProgress = updateValue.toInt()
    }
}