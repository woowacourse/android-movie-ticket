package woowacourse.movie.feature.seat

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R

class SeatSelectionActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            SeatSelectionActivity::class.java,
        ).also {
            it.putExtra(SeatSelectionActivity.SCREENING_ID, 0L)
            it.putExtra(SeatSelectionActivity.QUANTITY, 1)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectionActivity>(intent)

    @Test
    fun `스크린_위치가_화면에_표시된다`() {
        onView(withId(R.id.screen))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `좌석들이_화면에_표시된다`() {
        onView(withId(R.id.table_seat))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `영화_제목이_화면에_표시된다`() {
        onView(withId(R.id.tv_screen_movie_title))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `가격이_화면에_표시된다`() {
        onView(withId(R.id.tv_seat_price))
            .check(matches(ViewMatchers.isDisplayed()))
    }
}
