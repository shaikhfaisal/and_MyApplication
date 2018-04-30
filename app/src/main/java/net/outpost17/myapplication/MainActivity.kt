package net.outpost17.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.support.design.widget.Snackbar
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var yes_count = 0;
    var no_count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }



    fun handleYesClick(view: View) {



        yes_count++;
        findViewById<TextView>(R.id.fast_yes_count).setText(yes_count.toString())

        Log.i("MyApp", "Clicked yes ")
        val mySnackbar = Snackbar.make(view, R.string.fasted_confirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }

    fun handleNoClick(view: View) {



        no_count++;
        findViewById<TextView>(R.id.fast_no_count).setText(no_count.toString())

        Log.i("MyApp", "Clicked no ")
        val mySnackbar = Snackbar.make(view, R.string.fasted_nonconfirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }


}
