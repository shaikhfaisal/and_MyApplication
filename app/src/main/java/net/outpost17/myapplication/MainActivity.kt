package net.outpost17.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.support.design.widget.Snackbar
import android.widget.TextView
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDate


class MainActivity : AppCompatActivity() {


    val appLog:AppLog = AppLog()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidThreeTen.init(this);



    }



    fun handleYesClick(view: View) {


        val today = LocalDate.now()
        appLog.didFastOn(today)

        this.updateCounters()

        Log.i("MyApp", "Clicked yes ")
        val mySnackbar = Snackbar.make(view, R.string.fasted_confirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }

    fun handleNoClick(view: View) {


        val today = LocalDate.now()
        appLog.missedFastOn(today)

        this.updateCounters()

        Log.i("MyApp", "Clicked no ")

        val mySnackbar = Snackbar.make(view, R.string.fasted_nonconfirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }

    private fun updateCounters() {

        val totalNumberOfFasts = appLog.getTotalNumberOfFasts()
        findViewById<TextView>(R.id.fast_yes_count).setText(totalNumberOfFasts.toString())

        val totalNumberOfMissedFasts = appLog.getTotalNumberOfMissedFasts()
        findViewById<TextView>(R.id.fast_no_count).setText(totalNumberOfMissedFasts.toString())


    }


}
