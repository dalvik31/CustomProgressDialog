package com.dalvik.progresscustom

import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference
import android.content.res.ColorStateList
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView


class ProgressCustom private constructor(private val activity: WeakReference<AppCompatActivity>) {

    private var message: String = String()
    private var colorBackground: Int = 0
    private var colorText: Int = 0
    private var lottieFile: Int = 0
    private var cancelableDialog: Boolean = false
    private lateinit var alertDialog: AlertDialog
    private var callback: (DialogInterface) -> Unit = {}

    companion object {
        fun from(activity: AppCompatActivity) = ProgressCustom(WeakReference(activity))
    }

    fun message(message: String): ProgressCustom {
        this.message = message
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

    fun lottieAnimation(lottieFile: Int): ProgressCustom {
        this.lottieFile = lottieFile
        return this
    }

    fun setCancelCallback(callback: (DialogInterface) -> Unit): ProgressCustom {
        this.callback = callback
        return this
    }


    fun showProgress() {
        activity.get()?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater: LayoutInflater = activity.layoutInflater
            val view = inflater.inflate(R.layout.custom_dialog, null)
            val progressBar = view.findViewById<LottieAnimationView>(R.id.progress_animation)
            val containerCardView = view.findViewById<CardView>(R.id.containerProgress)
            val messageTexview = view.findViewById<TextView>(R.id.textMessage)

            builder.setView(view)
            builder.setCancelable(cancelableDialog)
            if (cancelableDialog) {
                builder.setOnCancelListener {
                    callback(it)
                }
            }
            alertDialog = builder.create()
            alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)


            if(lottieFile!=0){
                progressBar.setAnimation(
                    lottieFile
                )
            }

            messageTexview.setTextColor(
                ContextCompat.getColor(
                    activity,
                    if (colorText != 0) colorText else android.R.color.darker_gray
                )
            )


            containerCardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    activity,
                    if (colorBackground != 0) colorBackground else android.R.color.white
                )
            )

            messageTexview.text = message.ifBlank { "Cargando" }
            alertDialog.show()
        }
    }

    fun hideProgress() {
        alertDialog.run {
            dismiss()
        }
    }

}