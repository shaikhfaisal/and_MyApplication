package net.outpost17.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SaveTextActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    var mAuthListener: FirebaseAuth.AuthStateListener =  FirebaseAuth.AuthStateListener {

        val user: FirebaseUser ?= mAuth.currentUser

        if (user != null) {
            val display_text = findViewById(R.id.display_text) as TextView
            display_text.text = "Welcome " + mAuth.currentUser?.email
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_text)
    }

    override fun onStart() {
        super.onStart()

        mAuth.addAuthStateListener(mAuthListener)

        val username:String = intent.getStringExtra("username")
        val password:String = intent.getStringExtra("password")

        mAuth.signInWithEmailAndPassword(username, password)

    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener)
        }
    }

}
