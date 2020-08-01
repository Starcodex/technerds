package com.starcodex.interview.util

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.starcodex.interview.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun alertDisplayer(title: String, message: String, listener : DialogInterface.OnClickListener) {
        val builder: AlertDialog.Builder = AlertDialog.Builder (this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", listener)
        val ok: AlertDialog = builder.create()
        ok.show()
    }
}