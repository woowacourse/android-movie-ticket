package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 타이틀_글자를_표시_한다() {
        onView(withId(R.id.tv_title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 상영일_글자를_표시_한다() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 러닝타임_글자를_표시_한다() {
        onView(withId(R.id.tv_running_time))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 이미지를_표시_한다() {
        onView(withId(R.id.iv_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 버튼을_표시_한다() {
        onView(withId(R.id.btn_reserve))
            .check(matches(isDisplayed()))
    }
}
