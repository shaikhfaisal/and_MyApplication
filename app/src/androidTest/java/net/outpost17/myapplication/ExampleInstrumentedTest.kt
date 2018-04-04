package net.outpost17.myapplication

//import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4



import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.support.test.rule.ActivityTestRule
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
    fun loginScreen() {
        onView(withId(R.id.login_label_username)).check(matches(isDisplayed()))
        onView(withId(R.id.login_label_password)).check(matches(isDisplayed()))
        onView(withId(R.id.button_login)).check(matches(isDisplayed()))
    }

    @Test
    fun validateEmailAddressAndPassword() {
        onView(withId(R.id.login_username)).perform(clearText())
        onView(withId(R.id.login_username)).perform(typeText("test1example.com"))
//      onView(withId(R.id.login_password)).perform(typeText(""))
        onView(withId(R.id.button_login)).perform(click())
        onView(withId(R.id.login_username_validation)).check(matches(withText("Please enter a valid email address")))
        onView(withId(R.id.login_password_validation)).check(matches(withText("Please enter a password")))
    }

    @Test
    fun submitLoginDetails() {
        onView(withId(R.id.login_username)).perform(clearText())
        onView(withId(R.id.login_username)).perform(typeText("test1@example.com"))
        onView(withId(R.id.login_password)).perform(typeText("test1@example.com"))
        onView(withId(R.id.button_login)).perform(click())

        onView(withId(R.id.login_result)).check(matches(withText("Welcome test1@example.com")))

    }

}
