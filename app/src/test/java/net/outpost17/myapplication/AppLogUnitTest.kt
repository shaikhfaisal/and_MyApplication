package net.outpost17.myapplication

import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.Month
import org.threeten.bp.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class AppLogUnitTest {


    @Test
    fun testDidFastOnSingleDay () {

        val log = AppLog()
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfFasts())

    }

    @Test
    fun testDidFastOnMultipleDays () {
        val log = AppLog()
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 2))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 3))

        assertEquals(3, log.getTotalNumberOfFasts())
    }

    @Test
    fun testMultipleFastsOnSameDayCountAsOneFast() {
        val log = AppLog()
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfFasts())
    }




    @Test
    fun testMissedFastOnSingleDay () {

        val log = AppLog()
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfMissedFasts())

    }

    @Test
    fun testMissedFastOnMultipleDays () {
        val log = AppLog()
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 2))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 3))

        assertEquals(3, log.getTotalNumberOfMissedFasts())
    }

    @Test
    fun testMultipleMissedFastsOnSameDayCountAsOneMissedFast() {
        val log = AppLog()
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfMissedFasts())
    }



    @Test
    fun testCantFastAndMissFastOnSameDay() {
        val log = AppLog()

        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfMissedFasts())
        assertEquals(0, log.getTotalNumberOfFasts())



        log.didFastOn(LocalDate.of(2018, Month.MAY, 2))
        log.missedFastOn(LocalDate.of(2018, Month.MAY, 2))

        assertEquals(1, log.getTotalNumberOfFasts())
        assertEquals(1, log.getTotalNumberOfMissedFasts())
    }

}
