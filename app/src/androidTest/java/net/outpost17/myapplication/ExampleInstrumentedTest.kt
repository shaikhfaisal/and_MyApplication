package net.outpost17.myapplication

//import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.web.webdriver.DriverAtoms.getText
import android.support.test.runner.AndroidJUnit4



import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.support.test.rule.ActivityTestRule
import android.util.Log
import android.widget.TextView
import org.junit.Rule


/**
 * Instrumentation test, which will execute on an Android device.

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()

        assertEquals("net.outpost17.myapplication", appContext.packageName)
    }


    @Test
    fun homeScreen() {
        onView(withId(R.id.fast_question)).check(matches(isDisplayed()))
        onView(withId(R.id.fast_question_yes_button)).check(matches(isDisplayed()))
        onView(withId(R.id.fast_question_no_button)).check(matches(isDisplayed()))
    }

    @Test
    fun clickYes() {
        onView(withId(R.id.fast_question_yes_button)).perform(click())
        onView(withText(R.string.fasted_confirmation_text)).check(matches(isDisplayed()))
    }

    @Test
    fun clickNo() {
        onView(withId(R.id.fast_question_no_button)).perform(click())
        onView(withText(R.string.fasted_nonconfirmation_text)).check(matches(isDisplayed()))
    }


}
