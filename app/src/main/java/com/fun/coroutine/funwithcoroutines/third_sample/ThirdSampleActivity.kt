package com.`fun`.coroutine.funwithcoroutines.third_sample

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.`fun`.coroutine.funwithcoroutines.R
import kotlinx.android.synthetic.main.activity_third_sample.*
import kotlinx.coroutines.*

class ThirdSampleActivity : Activity() {
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_sample)

        btHideInFourSeconds.setOnClickListener { this.job = startHidingTextView() }
        btCancel.setOnClickListener { job?.cancel() }
    }

    private fun startHidingTextView() =
            GlobalScope.launch(mainDispatcher) {
                showProgressBar()

                withContext(ioDispatcher) {
                    Thread.sleep(4000)
                }

                hideProgressBar()
                tvMessage.visibility = View.GONE
            }

    private fun showProgressBar() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        pbLoading.visibility = View.GONE
    }

}