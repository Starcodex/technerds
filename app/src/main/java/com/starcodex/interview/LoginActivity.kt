package com.starcodex.interview

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser
import com.parse.LogInCallback;
import com.parse.ParseException
import com.starcodex.interview.list.ContactListActivity
import com.starcodex.interview.util.BaseActivity
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun loginUser(view: View){
        ParseUser.logInInBackground(userName.text.toString(), password.text.toString(), object: LogInCallback{
            override fun done(user: ParseUser?, e: ParseException?) {
                if (user != null) {
                    alertDisplayer("Sucessful Login","Welcome back ${userName.text}!",
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.cancel()
                            // don't forget to change the line below with the names of your Activities
                            val intent = Intent(this@LoginActivity, ContactListActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        })
                } else {
                    ParseUser.logOut()
                    Toast.makeText(applicationContext, e?.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    fun register(view: View){
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

}