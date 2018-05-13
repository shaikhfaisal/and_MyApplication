package net.outpost17.myapplication

import org.threeten.bp.LocalDate
import android.arch.persistence.room.Dao

class AppLog {

    var activity_days:MutableList<ActivityDate> = mutableListOf<ActivityDate>()

    fun didFastOn(date_of_fast: LocalDate) {
        this.recordFastStatusOn(date_of_fast, true)
    }


    fun missedFastOn(date_of_fast: LocalDate) {
        this.recordFastStatusOn(date_of_fast, false)
    }


    fun getTotalNumberOfFasts() : Int {
        return activity_days.filter { it.did_fast == true }.size
    }


    fun getTotalNumberOfMissedFasts() : Int {
        return activity_days.filter { it.did_fast == false }.size
    }


    private fun recordFastStatusOn(date_of_fast: LocalDate, was_fast_succesfull: Boolean) {
        val ad = ActivityDate(date_of_fast)
        ad.did_fast = was_fast_succesfull

        if (activity_days.contains(ad)) {
            activity_days.get(activity_days.indexOf(ad)).did_fast = was_fast_succesfull
        } else {
            activity_days.add(ad)
        }
    }

}

data class ActivityDate (val activity_date: LocalDate= LocalDate.now()) {

    var did_fast: Boolean = false

}

@Dao
public interface ActivityDateDAO {


}