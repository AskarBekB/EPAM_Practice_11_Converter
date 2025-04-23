package dev.androidbroadcast.converter

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import dev.androidbroadcast.converter.presentation.activity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFlowTest {

    @get:Rule
    val rule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun successfulLogin_opensMainActivity() {
        onView(withId(R.id.editUsername)).perform(typeText("user"), closeSoftKeyboard())
        onView(withId(R.id.editPassword)).perform(typeText("pass"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())
    }

    @Test
    fun failedLogin_showsErrorMessage() {
        onView(withId(R.id.editUsername)).perform(typeText("wrong"), closeSoftKeyboard())
        onView(withId(R.id.editPassword)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.errorText)).check(matches(withText("Login failed")))
    }
}
