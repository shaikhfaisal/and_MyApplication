package net.outpost17.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<RecyclerView>(R.id.button_login) as TextView
        button.setOnClickListener { v  ->

            val username = (findViewById(R.id.login_username) as EditText).text.toString()
            val password = (findViewById(R.id.login_password) as EditText).text.toString()

            if (!emailFormatIsValid(username))
                (findViewById(R.id.login_username_validation) as TextView).setText(R.string.login_username_validation_message)
            else
                (findViewById(R.id.login_username_validation) as TextView).setText(R.string.blank_string)

            if (!passwordFormatIsValid(password))
                (findViewById(R.id.login_password_validation) as TextView).setText(R.string.login_password_validation_message)
            else
                (findViewById(R.id.login_password_validation) as TextView).setText(R.string.blank_string)
            

            if (emailFormatIsValid(username) and passwordFormatIsValid(password)) {

                val login_result = findViewById(R.id.login_result) as TextView
                login_result.text = "Welcome test1@example.com"

                login(username, password)
            }

        }

    }

    private fun passwordFormatIsValid(password: String): Boolean {

        if (password.isNullOrEmpty())
            return false
        else
            return true

    }

    private fun emailFormatIsValid(email: String): Boolean {

        val email_regex :Regex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        if (!email.matches(email_regex))
            return false
        else
            return true

    }


}
