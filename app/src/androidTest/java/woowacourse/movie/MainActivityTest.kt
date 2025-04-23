package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @DisplayName("타이틀 글자 표시 테스트")
    @Test
    fun titleTest() {
        onView(withId(R.id.tv_title))
            .check(matches(isDisplayed()))
    }

    @DisplayName("상영일 글자 표시 테스트")
    @Test
    fun screeningDateTest() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(isDisplayed()))
    }

    @DisplayName("러닝타임 글자 표시 테스트")
    @Test
    fun runningTimeTest() {
        onView(withId(R.id.tv_running_time))
            .check(matches(isDisplayed()))
    }

    @DisplayName("이미지 표시 테스트")
    @Test
    fun posterTest() {
        onView(withId(R.id.iv_poster))
            .check(matches(isDisplayed()))
    }

    @DisplayName("버튼 표시 테스트")
    @Test
    fun buttonTest() {
        onView(withId(R.id.btn_reserve))
            .check(matches(isDisplayed()))
    }
}
