package ru.falseteam.tasks.ru.falseteam.tasks.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.falseteam.tasks.R
import ru.falseteam.tasks.ui.MainActivity


@RunWith(AndroidJUnit4::class)
@LargeTest
class EspressoTestExample {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun add_new_task() {
        val title = "Espresso"
        // Type text and then press the button.
        onView(withId(R.id.button_add))
                .perform(click())
        onView(withId(R.id.title)).perform(typeText(title), closeSoftKeyboard())
        onView(withId(R.id.btn_save)).perform(click())

        onView(withText(title)).check(matches(isDisplayed()))
    }
}