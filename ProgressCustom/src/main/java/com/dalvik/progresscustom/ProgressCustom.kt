package com.dalvik.progresscustom

import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import android.content.res.ColorStateList
import android.location.Location
import androidx.core.content.ContextCompat


class ProgressCustom private constructor(private val activity: WeakReference<AppCompatActivity>) {

    private var message: String = String()
    private var colorProgress: Int = 0
    private var colorBackground: Int = 0
    private var colorText: Int = 0
    private var cancelableDialog : Boolean = false
    private lateinit var alertDialog: AlertDialog
    private var callback: (DialogInterface) -> Unit = {}

    companion object {
        fun from(activity: AppCompatActivity) = ProgressCustom(WeakReference(activity))
    }

    fun message(message: String): ProgressCustom {
        this.message = message
        return this
    }

    fun colorProgress(colorProgress: Int): ProgressCustom {
        this.colorProgress = colorProgress
        return this
    }

    fun colorBackground(colorBackground: Int): ProgressCustom {
        this.colorBackground = colorBackground
        return this
    }

    fun colorText(colorText: Int): ProgressCustom {
        this.colorText = colorText
        return this
    }

    fun setCancelable(cancelableDialog: Boolean): ProgressCustom {
        this.cancelableDialog = cancelableDialog
        return this
    }

    fun setCancelable(cancelableDialog: Boolean,callback: (DialogInterface) -> Unit): ProgressCustom {
        this.callback = callback
        this.cancelableDialog = cancelableDialog
        return this
    }


    fun showProgress() {
        activity.get()?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater: LayoutInflater = activity.layoutInflater
            val view = inflater.inflate(R.layout.custom_dialog, null)
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
            val messageTexview = view.findViewById<TextView>(R.id.textMessage)
            messageTexview.text = message
            builder.setView(view)
            builder.setCancelable(cancelableDialog)
            if(cancelableDialog){
                builder.setOnCancelListener {
                    callback(it)
                }
            }
            alertDialog = builder.create()

            if (colorProgress != 0 )
                progressBar.indeterminateTintList = ColorStateList.valueOf(ContextCompat.getColor(activity,colorProgress))
            if(colorText!= 0 )
                messageTexview.setTextColor(ContextCompat.getColor(activity,colorText))
            if(colorBackground != 0)
                alertDialog.window!!.setBackgroundDrawableResource(colorBackground)
            alertDialog.show()
        }
    }

    fun hideProgress() {
        alertDialog.run {
            dismiss()
        }
    }
}