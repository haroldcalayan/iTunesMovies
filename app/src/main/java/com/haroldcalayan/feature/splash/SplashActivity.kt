package com.haroldcalayan.feature.splash

import android.content.Intent
import com.haroldcalayan.BR
import com.haroldcalayan.R
import com.haroldcalayan.common.base.BaseActivity
import com.haroldcalayan.databinding.ActivitySplashBinding
import com.haroldcalayan.feature.master.MasterActivity
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun getLayout() = R.layout.activity_splash

    override fun getBindingVariable() = BR.viewModel

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    override fun initViews() {
        super.initViews()
        hideSystemUI()
        startTimer()
    }

    private fun startTimer() {
        activityScope.launch {
            delay(SPLASH_SCREEN_LIFE)
            openMaster()
            finish()
        }
    }

    private fun openMaster() {
        Intent(this, MasterActivity::class.java).apply {
            startActivity(this)
        }
    }

    companion object {
        private const val SPLASH_SCREEN_LIFE = 3000L
    }
}