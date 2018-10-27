package com.`fun`.coroutine.funwithcoroutines

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.`fun`.coroutine.funwithcoroutines.first_sample.FirstSampleActivity
import com.`fun`.coroutine.funwithcoroutines.second_sample.SecondSampleActivity
import com.`fun`.coroutine.funwithcoroutines.third_sample.ThirdSampleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btFirstExample.setOnClickListener { startActivity(Intent(this, FirstSampleActivity::class.java)) }
        btSecondExample.setOnClickListener { startActivity(Intent(this, SecondSampleActivity::class.java)) }
        btThirdExample.setOnClickListener { startActivity(Intent(this, ThirdSampleActivity::class.java)) }
    }
}
