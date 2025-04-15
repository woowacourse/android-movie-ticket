package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @DisplayName("영화 아이템 항목 1개 출력 테스트")
    @Test
    fun movieListTest() {
        onView(withId(R.id.tv_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }
}
