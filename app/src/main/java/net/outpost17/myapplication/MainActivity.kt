package net.outpost17.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.support.design.widget.Snackbar
import android.widget.TextView
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDate
import android.arch.persistence.room.Room
import android.widget.DatePicker
import android.app.DatePickerDialog
import org.threeten.bp.ZoneId


class MainActivity : AppCompatActivity() {


    private lateinit var appLog:AppLog

    private lateinit var selectedDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidThreeTen.init(this);
        selectedDate = LocalDate.now(ZoneId.systemDefault())

        val db = Room.databaseBuilder(
                    applicationContext,
                    ActivityDatabase::class.java,
                    "activity_database"
                )
                .allowMainThreadQueries()
                .build()

        this.appLog = AppLog(db)

    }

    override fun onStart() {
        super.onStart()
        this.updateCounters()
    }



    fun handleYesClick(view: View) {

        appLog.didFastOn(selectedDate)

        this.updateCounters()

        Log.i("MyApp", "Clicked yes for " + selectedDate.toString())
        val mySnackbar = Snackbar.make(view, R.string.fasted_confirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }

    fun handleNoClick(view: View) {

        appLog.missedFastOn(selectedDate)

        this.updateCounters()

        Log.i("MyApp", "Clicked no for " + selectedDate.toString())

        val mySnackbar = Snackbar.make(view, R.string.fasted_nonconfirmation_text, Snackbar.LENGTH_SHORT)
        mySnackbar.show();
    }

    private fun updateCounters() {

        val totalNumberOfFasts = appLog.getTotalNumberOfFasts()
        findViewById<TextView>(R.id.fast_yes_count).setText(totalNumberOfFasts.toString())

        val totalNumberOfMissedFasts = appLog.getTotalNumberOfMissedFasts()
        findViewById<TextView>(R.id.fast_no_count).setText(totalNumberOfMissedFasts.toString())


    }

    fun showDatePickerDialog(v: View) {
        Log.i("MyApp", "Clicked on calendar view")
        val d = DatePickerDialog(
                this,
                android.R.style.Theme_Material_Light_Dialog,
                dp_listener(),
                LocalDate.now().year,
                LocalDate.now().monthValue-1,
                LocalDate.now().dayOfMonth
        )
        d.show()
    }

    inner class dp_listener(): DatePickerDialog.OnDateSetListener
    {

        override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
            selectedDate = LocalDate.of(year, month+1, dayOfMonth)
            Log.i("MyApp", "Date Picker listener called with $year-$month-$dayOfMonth")
        }

    }


}
