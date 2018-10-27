package com.`fun`.coroutine.funwithcoroutines.first_sample

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.`fun`.coroutine.funwithcoroutines.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_first_sample.*
import kotlinx.coroutines.*

class FirstSampleActivity : Activity() {
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_sample)
        firstCase()
        secondCase()
    }

    private fun firstCase() {
        CoroutineScope(mainDispatcher).launch {
            showProgressBar() //сейчас я на UI потоке

            val imageUrl = withContext(ioDispatcher) {
                //а тут не ui, ui пождет(suspend) пока этот не выполнится
                loadImageUrl()
            }

            hideProgressBar()
            showResult(imageUrl)//и снова на UI
        }
    }

    private fun secondCase() {
        CoroutineScope(mainDispatcher).launch {
            val firstResult = withContext(ioDispatcher) { loadFirstTextResult() }
            val secondResult = withContext(ioDispatcher) { loadSecondTextResult() }

            tvResult.text = "Combine result = $firstResult + $secondResult"
        }
    }

    private fun showProgressBar() {
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        pbLoading.visibility = View.GONE
    }

    private fun loadImageUrl(): String {
        Thread.sleep(3000)
        return "https://vistanews.ru/uploads/posts/2018-08/1533537546_36498615_643480896033098_4503030638693056512_n.jpg"
    }

    private fun loadFirstTextResult(): String {
        Thread.sleep(1000)
        return "First hard result"
    }

    private fun loadSecondTextResult(): String {
        Thread.sleep(2000)
        return "Second hard result"
    }

    private fun showResult(imageUrl: String) {
        Glide.with(this).load(imageUrl).into(ivResult)
    }
}