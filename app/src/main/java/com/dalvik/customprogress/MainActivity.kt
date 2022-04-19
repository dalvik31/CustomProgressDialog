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
                .colorText(R.color.purple_200)
                .colorProgress(R.color.purple_200)
                .colorBackground(R.color.purple_500)
                .showProgress()
        }

        buttonHideProgress.setOnClickListener{
            progressCustom.hideProgress()
        }
    }
}