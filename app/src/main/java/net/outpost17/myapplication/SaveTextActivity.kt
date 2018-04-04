package net.outpost17.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SaveTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_text)
    }

    override fun onStart() {
        super.onStart()

        val username:String = intent.getStringExtra("username")
        val password:String = intent.getStringExtra("password")

        val display_text = findViewById(R.id.display_text) as TextView
        display_text.text = "Welcome test1@example.com"

        login(username, password)

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
