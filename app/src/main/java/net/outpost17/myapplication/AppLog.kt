package net.outpost17.myapplication

import org.threeten.bp.LocalDate


class AppLog {

    var days_fasted:MutableList<LocalDate> = mutableListOf<LocalDate>()

    var days_missed_fasted:MutableList<LocalDate> = mutableListOf<LocalDate>()



    private fun dateHasNotNeenLogged(date: LocalDate) : Boolean {

        if( ! days_fasted.contains(date) and ! days_missed_fasted.contains(date)) {
            return true
        }

        return false
    }

    fun didFastOn(date_of_fast: LocalDate) {

        if (dateHasNotNeenLogged (date_of_fast)) {
            days_fasted.add(date_of_fast)
        }

    }

    fun getTotalNumberOfFasts() : Int {
        return days_fasted.size
    }



    fun missedFastOn(date_of_fast: LocalDate) {
        if (dateHasNotNeenLogged (date_of_fast)) {
            days_missed_fasted.add(date_of_fast)
        }

    }

    fun getTotalNumberOfMissedFasts() : Int {
        return days_missed_fasted.size
    }

}