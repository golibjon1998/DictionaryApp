package com.example.dictionaryapp.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment


fun logd(mess: String, tag: String = "myLogs") {
    Log.d(tag, mess)
}

//fun isLogsEnabled() = BuildConfig.DEBUG

fun View.show() {
    this.visibility = (View.VISIBLE)
}

fun View.hide() {
    this.visibility = (View.GONE)
}

fun View.invisible() {
    this.visibility = (View.INVISIBLE)
}

fun View.isVisible(): Boolean {
    return this.visibility == (View.VISIBLE)
}

fun View.isNotVisible(): Boolean {
    return this.visibility != (View.VISIBLE)
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message, duration)
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.hideKeyBoard() {
    val view = this.currentFocus
    view ?: return
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

@TargetApi(21)
fun View.hideAnimWithReveal(
    duration: Long = 200,
    endAction: () -> Unit = {},
    x: Int = -1,
    y: Int = -1
) {
    if (isVisible()) {
        val cx = if (x >= 0) x else width / 2
        val cy = if (y >= 0) y else height / 2
        val initialRadius = Math.hypot(cx.toDouble(), cy.toDouble())

        val revealAnim =
            ViewAnimationUtils.createCircularReveal(this, cx, cy, initialRadius.toFloat(), 0f)
        revealAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                hide()
                endAction()
            }
        })

        revealAnim.start()
    } else {
        endAction()
    }
}

@TargetApi(21)
fun View.showAnimWithReveal(
    duration: Long = 200,
    endAction: () -> Unit = {},
    x: Int = -1,
    y: Int = -1
) {
    if (isNotVisible()) {
        this.doOnPreDraw {
            alpha = 1f
            translationY = 0f
            translationX = 0f
            val centerX = if (x >= 0) x else width / 2
            val centerY = if (y >= 0) y else height / 2
            val finalRadius = Math.hypot(centerX.toDouble(), centerY.toDouble())

            val revealAnim = ViewAnimationUtils.createCircularReveal(
                this,
                centerX,
                centerY,
                0f,
                finalRadius.toFloat()
            )
            revealAnim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(p0: Animator?) {
                    endAction()
                }
            })

            show()
            revealAnim.start()
        }
    } else {
        endAction()
    }
}
fun View.doOnPreDraw(callback: () -> Unit) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            callback.invoke()
            return true
        }
    })
}

fun View.hideAnimWithScale(
    duration: Long = 200,
    interpolator: Interpolator = AccelerateDecelerateInterpolator()
) {
    if (this.isVisible()) {
        this.animate()
            .alpha(0f)
            .scaleX(0f)
            .scaleY(0f)
            .setDuration(duration)
            .setInterpolator(interpolator)
            .withEndAction { this.hide() }
            .start()
    }
}

fun View.showAnimWithScale(
    duration: Long = 200,
    interpolator: Interpolator = AccelerateDecelerateInterpolator()
) {
    if (this.isNotVisible()) {
        doOnPreDraw {
            alpha = 0f
            scaleY = 0f
            scaleX = 0f
            this.show()
            this.animate()
                .alpha(1f)
                .scaleY(1f)
                .scaleX(1f)
                .setInterpolator(interpolator)
                .setDuration(duration)
                .start()
        }
    }
}


fun View.showAnimWithSlideUp(duration: Long = 200, endAction: () -> Unit = {}) {
    if (isNotVisible()) {
        show()
        this.doOnPreDraw {
            translationY = height.toFloat()
            alpha = 0.5f
            animate()
                .alpha(1f)
                .translationY(0f)
                .withEndAction(endAction)
                .setInterpolator(DecelerateInterpolator())
                .setDuration(duration)
                .start()
        }
    } else {
        endAction()
    }
}

fun View.hideAnimWithSlideDown(duration: Long = 200, endAction: () -> Unit = {}) {
    if (isVisible()) {
        animate()
            .alpha(0.5f)
            .translationY(height.toFloat())
            .withEndAction {
                hide()
                endAction()
            }
            .setInterpolator(AccelerateInterpolator())
            .setDuration(duration)
            .start()
    } else {
        endAction()
    }
}

fun Context.browse(link: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)

    } catch (e: Exception) {
        showToast(e.localizedMessage ?: "Error")
    }
}