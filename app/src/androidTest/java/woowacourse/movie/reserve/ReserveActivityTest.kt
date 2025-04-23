package woowacourse.movie.reserve

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.HARRY_POTTER_MOVIE
import woowacourse.movie.R
import woowacourse.movie.ui.reserve.ReserveActivity

class ReserveActivityTest {
    private lateinit var intent: Intent
    private val movie = HARRY_POTTER_MOVIE
    private lateinit var scenario: ActivityScenario<ReserveActivity>

    @Before
    fun setUp() {
        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReserveActivity::class.java,
            ).apply {
                putExtra("key_reserve_activity_movie", movie)
            }
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun shouldDisplayTitleText() {
        onView(withId(R.id.tv_title))
            .check(matches(withText("해리포터")))
    }

    @Test
    fun shouldDisplayScreeningDateText() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText("2025.04.30 ~ 2025.05.04")))
    }

    @Test
    fun shouldDisplayRunningTimeText() {
        onView(withId(R.id.tv_running_time))
            .check(matches(withText("152분")))
    }

    @Test
    fun shouldDisplayTicketCount() {
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("1")))
    }

    @Test
    fun shouldIncreaseTicketCount() {
        onView(withId(R.id.btn_plus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("2")))
    }

    @Test
    fun shouldDecreaseTicketCount() {
        onView(withId(R.id.btn_plus))
            .perform(click())
        onView(withId(R.id.btn_minus))
            .perform(click())
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("1")))
    }

    @Test
    fun shouldRotateAndKeepCount() {
        onView(withId(R.id.btn_plus))
            .perform(click())
        rotateScreen()
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("2")))
    }

    private fun rotateScreen() {
        scenario.onActivity { activity ->
            val currentOrientation = activity.resources.configuration.orientation
            activity.requestedOrientation =
                if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                } else {
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }
        }
    }
}
