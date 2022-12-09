package com.kerencev.rickandmorty

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpSplashScreen()
        setContentView(R.layout.activity_main)
    }

    private fun setUpSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            installSplashScreen().apply {
                setOnExitAnimationListener { splashScreenProvider ->
                    ObjectAnimator.ofFloat(
                        splashScreenProvider.view,
                        View.TRANSLATION_Y,
                        0f,
                        -splashScreenProvider.view.height.toFloat(),
                    ).apply {
                        duration = SPLASH_DURATION
                        interpolator = AnticipateInterpolator()
                        doOnEnd {
                            splashScreenProvider.remove()
                        }
                    }.start()
                }
            }
        } else {
            setTheme(R.style.Theme_RickAndMorty)
        }
    }

    companion object {
        private const val SPLASH_DURATION = 800L
    }
}
