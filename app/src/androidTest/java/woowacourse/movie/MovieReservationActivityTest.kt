package woowacourse.movie

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.fixtures.context
import woowacourse.movie.presentation.reservation.booking.MovieReservationActivity

class MovieReservationActivityTest {
    @get:Rule
    val activityRule =
        ActivityScenarioRule<MovieReservationActivity>(
            Intent(
                context,
                MovieReservationActivity::class.java,
            ).apply {
                putExtra(MovieReservationActivity.EXTRA_SCREEN_MOVIE_ID, 1L)
            },
        )

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.detail_movie)).check(matches(isDisplayed()))
    }
}
