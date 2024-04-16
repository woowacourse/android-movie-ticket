package woowacourse.movie

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `앱_실행시_영화리스트가_화면에_표시된다`() {
        onView(withId(R.id.list_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `사용자가_영화를_선택하면_예약화면으로_넘어간다`() {
        onData(anything())
            .inAdapterView(withId(R.id.list_view))
            .atPosition(0)
            .perform(click())

        onView(withId(R.id.movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }
}
