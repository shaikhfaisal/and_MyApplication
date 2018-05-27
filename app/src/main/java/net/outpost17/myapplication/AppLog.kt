package net.outpost17.myapplication

import android.arch.persistence.room.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter.*

class AppLog (db: ActivityDatabase) {

    var activity_days:MutableList<ActivityDate> = mutableListOf<ActivityDate>()

    val db: ActivityDatabase = db

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
            db.activityDateDAO().update(ad)
        } else {
            activity_days.add(ad)

            db.activityDateDAO().insertall(ad)
        }
    }

}

@TypeConverters(LocalDateTypeConvertor::class)
@Entity
data class ActivityDate (@PrimaryKey var activity_date: LocalDate = LocalDate.now()) {

    var did_fast: Boolean = false

}

class LocalDateTypeConvertor {

    @TypeConverter
    fun localdate_to_date(localDate: LocalDate) : String {
        return localDate.format(BASIC_ISO_DATE)
    }

    @TypeConverter
    fun date_to_localdate(date_string: String): LocalDate {
        return BASIC_ISO_DATE.parse(date_string, org.threeten.bp.LocalDate::from)
    }
}


@Dao
interface ActivityDateDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertall (vararg activityDate : ActivityDate)

    @Query("select * from activitydate")
    fun getAll(): List<ActivityDate>

    @Update
    fun update (activityDate: ActivityDate)

}

@Database (entities = arrayOf(ActivityDate::class), version = 1)
abstract class ActivityDatabase : RoomDatabase() {

    abstract fun activityDateDAO(): ActivityDateDAO

    companion object {
        var INSTANCE :ActivityDatabase? = null

//        fun getDatabase(context: Context): ActivityDatabase? {
//
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder<ActivityDatabase>(
//                        context.applicationContext(),
//                        ActivityDatabase::class.java,
//                        "RamdanDiary.db"
//                )
//                        .allowMainThreadQueries()
//                        .build()
//            }
//
//            return INSTANCE
//
//
//        }
//
//
//        fun getInMemoryDatabase(context: Context): ActivityDatabase? {
//
//            if (INSTANCE == null) {
//
//                INSTANCE = Room.inMemoryDatabaseBuilder<ActivityDatabase>(
//                        context.applicationContext,
//                        ActivityDatabase::class.java
//                )
//                        // To simplify the codelab, allow queries on the main thread.
//                        // Don't do this on a real app! See PersistenceBasicSample for an example.
//                        .allowMainThreadQueries()
//                        .build()
//            }
//            return INSTANCE
//        }

    }

}