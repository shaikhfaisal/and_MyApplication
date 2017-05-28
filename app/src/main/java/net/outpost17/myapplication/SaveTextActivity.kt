package net.outpost17.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SaveTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_text)

        val display_text = findViewById(R.id.display_text) as TextView

        display_text.text = intent.getStringExtra("username") + "-" + intent.getStringExtra("password")


    }


}
