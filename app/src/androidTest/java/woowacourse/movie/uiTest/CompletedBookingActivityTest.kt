package woowacourse.movie.uiTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.completedbooking.CompletedBookingActivity

class CompletedBookingActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(CompletedBookingActivity::class.java)

    @Test
    fun haveCancelInfo() {
        onView(withId(R.id.cancel_info_Text))
            .check(matches(withText(R.string.movie_title)))
    }
}
