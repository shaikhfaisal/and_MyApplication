package net.outpost17.myapplication

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.threeten.bp.Month
import org.threeten.bp.LocalDate
import org.mockito.Mockito.*


/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class AppLogUnitTest {


    lateinit var log: AppLog

    @Before
    /*
        Initialise mocked database and setup AppLog
     */
    fun init() {

        val mockActivityDateDao: ActivityDateDAO = mock(ActivityDateDAO::class.java)
        val mockedDatabase: ActivityDatabase = mock(ActivityDatabase::class.java)

        `when`(mockedDatabase.activityDateDAO()).thenReturn(mockActivityDateDao)

        this.log = AppLog(mockedDatabase)

    }


    @Test
    fun testDidFastOnSingleDay () {

        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfFasts())
    }


    @Test
    fun testDidFastOnMultipleDays () {

        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 2))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 3))

        assertEquals(3, log.getTotalNumberOfFasts())
    }


    @Test
    fun testMultipleFastsOnSameDayCountAsOneFast() {

        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfFasts())
    }


    @Test
    fun testMissedFastOnSingleDay () {

        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfMissedFasts())

    }


    @Test
    fun testMissedFastOnMultipleDays () {

        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 2))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 3))

        assertEquals(3, log.getTotalNumberOfMissedFasts())
    }


    @Test
    fun testMultipleMissedFastsOnSameDayCountAsOneMissedFast() {

        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(1, log.getTotalNumberOfMissedFasts())
    }


    @Test
    fun testCantFastAndMissFastOnSameDay() {

        log.missedFastOn(LocalDate.of(2018,Month.MAY, 1))
        log.didFastOn(LocalDate.of(2018,Month.MAY, 1))

        assertEquals(0, log.getTotalNumberOfMissedFasts())
        assertEquals(1, log.getTotalNumberOfFasts())


        log.didFastOn(LocalDate.of(2018, Month.MAY, 2))
        log.missedFastOn(LocalDate.of(2018, Month.MAY, 2))

        assertEquals(1, log.getTotalNumberOfFasts())
        assertEquals(1, log.getTotalNumberOfMissedFasts())
    }


    @Test
    fun testDidFastMeansNegatingAnyNotFastsOnTheSameDay() {

        log.missedFastOn(LocalDate.of(2018, Month.AUGUST, 28))
        assertEquals(1, log.getTotalNumberOfMissedFasts())

        log.didFastOn(LocalDate.of(2018, Month.AUGUST, 28))

        assertEquals(0, log.getTotalNumberOfMissedFasts())
        assertEquals(1, log.getTotalNumberOfFasts())


    }


    @Test
    fun testMissedFastMeansNegatingFastsOnTheSameDay() {

        log.didFastOn(LocalDate.of(2018, Month.AUGUST, 28))
        assertEquals(1, log.getTotalNumberOfFasts())

        log.missedFastOn(LocalDate.of(2018, Month.AUGUST, 28))

        assertEquals(1, log.getTotalNumberOfMissedFasts())
        assertEquals(0, log.getTotalNumberOfFasts())


    }


    @Test
    /*
        Tests database calls come through correctly from the AppLog service.
     */
    fun testDatabaseGetsUpdatedForNewAndExistingDay () {

        val mockActivityDateDao: ActivityDateDAO = mock(ActivityDateDAO::class.java)
        val mockedDatabase: ActivityDatabase = mock(ActivityDatabase::class.java)
        `when`(mockedDatabase.activityDateDAO()).thenReturn(mockActivityDateDao)

        val a = AppLog(mockedDatabase)

        val ld = LocalDate.of(2018,Month.MAY, 1)
        val ad = ActivityDate(ld)
        ad.did_fast = true


        a.didFastOn(ld)
        verify(mockActivityDateDao).insertall(ad)

        a.missedFastOn(ld)
        a.didFastOn(ld)
        verify(mockActivityDateDao, times(2)).update(ad)
    }

    @Test
    fun testReadsInitialStateCorrectlyFromDatabase() {

        val mockActivityDateDao: ActivityDateDAO = mock(ActivityDateDAO::class.java)

        val ld1 = LocalDate.of(2018,Month.MAY, 1)
        val ad1 = ActivityDate(ld1)
        ad1.did_fast = true

        val ld2 = LocalDate.of(2018,Month.MAY, 2)
        val ad2 = ActivityDate(ld2)
        ad2.did_fast = true

        val ld3 = LocalDate.of(2018,Month.MAY, 3)
        val ad3 = ActivityDate(ld3)
        ad3.did_fast = false

        val ads: MutableList<ActivityDate> = mutableListOf(ad1, ad2, ad3)

        `when`(mockActivityDateDao.getAll()).thenReturn(ads)

        val mockedDatabase: ActivityDatabase = mock(ActivityDatabase::class.java)
        `when`(mockedDatabase.activityDateDAO()).thenReturn(mockActivityDateDao)

        val a = AppLog(mockedDatabase)


        assertEquals(1, a.getTotalNumberOfMissedFasts())
        assertEquals(2, a.getTotalNumberOfFasts())

    }

}
