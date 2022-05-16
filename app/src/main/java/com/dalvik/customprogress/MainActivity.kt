package com.dalvik.customprogress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dalvik.progresscustom.ProgressCustom
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val progressCustom = ProgressCustom.from(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonShowProgress.setOnClickListener{
            progressCustom
                .setCancelable(true)
                .colorText(android.R.color.darker_gray)
                .message("Custom message")
                .colorBackground(android.R.color.white)
                .showProgress()
        }

        buttonHideProgress.setOnClickListener{
            progressCustom.hideProgress()
        }
    }
}