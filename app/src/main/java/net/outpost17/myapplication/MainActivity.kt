package net.outpost17.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.button_login).setOnClickListener {



            val username_field = findViewById(R.id.login_username) as EditText
            val password_field = findViewById(R.id.login_password) as EditText


            var intent = Intent(this, SaveTextActivity::class.java)
            intent.putExtra("username", username_field.text.toString())
            intent.putExtra("password", password_field.text.toString())

            startActivity(intent)
        }

    }


}
