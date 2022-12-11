package com.kerencev.rickandmorty.presentation.main

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.kerencev.rickandmorty.R
import com.kerencev.rickandmorty.databinding.ActivityMainBinding
import com.kerencev.rickandmorty.navigation.CharactersScreen
import com.kerencev.rickandmorty.navigation.EpisodesScreen
import com.kerencev.rickandmorty.navigation.LocationsScreen
import com.kerencev.rickandmorty.presentation.base.fragment.OnBackPressedListener
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavigationActivity {

    private val navigator = AppNavigator(this, R.id.fragmentContainer)
    private val navigatorHolder: NavigatorHolder = getKoin().get()
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigationClicks()
        if (savedInstanceState == null) {
            viewModel.replaceStartScreen()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        viewModel.onBackPressed()
    }

    override fun setCurrentNavigationTab(tab: NavigationTab) {
        when (tab) {
            NavigationTab.CHARACTERS -> binding.bottomNavigation.menu.getItem(0).isChecked = true
            NavigationTab.LOCATIONS -> binding.bottomNavigation.menu.getItem(1).isChecked = true
            NavigationTab.EPISODES -> binding.bottomNavigation.menu.getItem(2).isChecked = true
        }
    }

    override fun showBottomNavigation(isShow: Boolean) {
        binding.bottomNavigation.isVisible = isShow
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

    private fun setUpNavigationClicks() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.actionCharacters -> {
                    viewModel.navigateTo(CharactersScreen)
                }
                R.id.actionEpisodes -> {
                    viewModel.navigateTo(EpisodesScreen)
                }
                R.id.actionLocation -> {
                    viewModel.navigateTo(LocationsScreen)
                }
            }

            true
        }
    }

    companion object {
        private const val SPLASH_DURATION = 800L
    }
}
