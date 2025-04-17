package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `화면에_해리포터_영화의_제목이_표시된다`() {
        onView(withId(R.id.title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `화면에_해리포터_영화의_상영날짜가_표시된다`() {
        onView(withId(R.id.start_date))
            .check(matches(withText("2025.4.1")))
    }

    @Test
    fun `화면에_해리포터_영화의_러닝타임이_표시된다`() {
        onView(withId(R.id.running_time))
            .check(matches(withText("152분")))
    }
}
