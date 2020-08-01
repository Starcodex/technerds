package com.starcodex.interview

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseException
import com.parse.ParseUser
import com.parse.SignUpCallback
import com.starcodex.interview.util.BaseActivity
import kotlinx.android.synthetic.main.register_activity.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun registerUser(view: View){

        val user = ParseUser()
// Set the user's username and password, which can be obtained by a forms
        user.setUsername(userName.text.toString())
        user.setPassword(password.text.toString())
        user.signUpInBackground(object: SignUpCallback {
            override fun done(e: ParseException?) {
                if (e == null) {
                    alertDisplayer("Sucessful Registration!","Welcome ${userName.text}!",
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.cancel()
                            // don't forget to change the line below with the names of your Activities
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        })
                } else {
                    ParseUser.logOut()
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show();
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_register_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_login -> {
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun alertDisplayer(title: String, message: String, listener : DialogInterface.OnClickListener) {
        val builder: AlertDialog.Builder = AlertDialog.Builder (this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", listener)
        val ok: AlertDialog = builder.create()
        ok.show()
    }

    fun goToLogin(view: View){
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}