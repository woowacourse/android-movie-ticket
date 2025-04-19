package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test

class ReserveActivityTest {
    private lateinit var intent: Intent
    private val movie = HARRY_POTTER_MOVIE

    @Before
    fun setUp() {
        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReserveActivity::class.java,
            ).apply {
                putExtra("movie", movie)
            }
        ActivityScenario.launch<ReserveActivity>(intent)
        buttonPlus()
    }

    private fun buttonPlus() {
        onView(withId(R.id.btn_plus))
            .perform(click())
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
    fun shouldIncreaseTicketCount() {
        onView(withId(R.id.btn_plus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("3")))
    }

    @Test
    fun shouldDecreaseTicketCount() {
        onView(withId(R.id.btn_minus))
            .perform(click())

        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("1")))
    }
}
