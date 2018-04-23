package net.outpost17.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.support.design.widget.Snackbar



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



    fun handleYesClick(view: View) {

        Log.i("MyApp", "Clicked yes ")
        val mySnackbar = Snackbar.make(view, R.string.fasted_confirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }

    fun handleNoClick(view: View) {

        Log.i("MyApp", "Clicked no ")
        val mySnackbar = Snackbar.make(view, R.string.fasted_nonconfirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }


}
