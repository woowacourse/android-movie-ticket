package woowacourse.movie.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.selection.SeatSelectActivity

class SeatSelectActivityUITest {
    @get:Rule
    val activityRule = ActivityScenarioRule(SeatSelectActivity::class.java)

    @Test
    fun 좌석선택() {
        // Given

        // when, 좌석을 선택한다
        onView(withId(R.id.a1)).perform(click())

        // then, 해당 좌석이 선택된다
        onView(withId(R.id.a1)).check(matches(isSelected()))
    }

    @Test
    fun 재선택() {
        // Given, 좌석이 선택되어 있다
        onView(withId(R.id.a1)).perform(click())

        // when, 선택 된 좌석을 다시 선택한다
        onView(withId(R.id.a1)).perform(click())

        // then, 해당 좌석이 선택 해제 된다
        onView(withId(R.id.a1)).check(matches(isSelected()))
    }
}
