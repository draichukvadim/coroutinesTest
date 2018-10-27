package com.`fun`.coroutine.funwithcoroutines.second_sample

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.`fun`.coroutine.funwithcoroutines.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_second_sample.*
import kotlinx.coroutines.*

class SecondSampleActivity : Activity() {
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_sample)
        btFailWithTimeout.setOnClickListener { failWithTimeout() }
        btCompletedBeforeTimeout.setOnClickListener { completeWithTimeout() }
    }

    private fun failWithTimeout() {
        GlobalScope.launch(mainDispatcher) {
            showProgressBar()

            val task = async(ioDispatcher) {
                loadInTwoSeconds()
            }
            val result = withTimeoutOrNull(1000) { task.await() }

            hideProgressBar()
            showResult(result)
        }
    }

    private fun completeWithTimeout() {
        GlobalScope.launch(mainDispatcher) {
            ivResult.visibility = View.GONE
            showProgressBar()

            val task = async(ioDispatcher) {
                loadInTwoSeconds()
            }
            val result = withTimeoutOrNull(5000) { task.await() }

            hideProgressBar()
            showResult(result)
        }
    }

    private fun loadInTwoSeconds(): String {
        Thread.sleep(2000)
        return "https://cdn.cultofmac.com/wp-content/uploads/2008/10/post-4062-image-28f38f1e6d36d28c62cb954d3a71ded0.jpg"
    }

    private fun showProgressBar() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        pbLoading.visibility = View.GONE
    }

    private fun showResult(result: String?) {
        ivResult.visibility = View.VISIBLE
        if (result != null) {
            Glide.with(this).load(result).into(ivResult)
        } else {
            val error = "https://ae01.alicdn.com/kf/HTB1ATzpOVXXXXcwXFXXq6xXFXXXE/Custom-Canvas-Art-Grumpy-Cat-Poster-Grumpy-Cat-Wallpaper-Funny-Animals-Wall-Stickers-Nope-Mural-Christmas.jpg"
            Glide.with(this).load(error).into(ivResult)
        }
    }

}