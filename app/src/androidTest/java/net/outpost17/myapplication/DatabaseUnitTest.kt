package net.outpost17.myapplication

import org.junit.Assert
import org.junit.Test
import android.arch.persistence.room.Room
import org.junit.Before
import android.content.Context
import android.support.test.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


class DatabaseUnitTest {

    lateinit private var mDatabase: ActivityDatabase


    @Before
    @Throws(Exception::class)
    fun initDb() {

        mDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                ActivityDatabase::class.java)
                .build()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        mDatabase?.close()
    }


    @Test
    fun insertAndGetActivityDate() {
        // When inserting a new user in the data source

        val ac1 = ActivityDate(LocalDate.of(2018,1,5))
        val ac2 = ActivityDate(LocalDate.of(2018,1,6))

        mDatabase?.activityDateDAO()?.insertall(
                ac1, ac2
        )

        //The users can be retrieved
        val activity_dates = mDatabase?.activityDateDAO()?.getAll()
        assertEquals(2, activity_dates?.size)

        assertEquals("20180105", activity_dates?.get(0)?.activity_date?.format(DateTimeFormatter.BASIC_ISO_DATE))
        assertEquals("20180106", activity_dates?.get(1)?.activity_date?.format(DateTimeFormatter.BASIC_ISO_DATE))


    }
}