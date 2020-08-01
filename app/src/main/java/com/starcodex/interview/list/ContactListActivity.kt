package com.starcodex.interview.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.starcodex.interview.R

class ContactListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_contact_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}