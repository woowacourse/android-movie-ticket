package woowacourse.movie.uiTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.activity.CompleteActivity

class CompleteActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(CompleteActivity::class.java)

    @Test
    fun haveCancelInfo() {
        onView(withId(R.id.cancel_info_Text))
            .check(matches(withText("영화 상영 시작 15분 전까지\n 취소가 가능합니다.")))
    }
}
