package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test

class ReservationResultActivityTest {
    private lateinit var intent: Intent
    private val reservation = HARRY_POTTER_RESERVATION

    @Before
    fun setUp() {
        intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReservationResultActivity::class.java,
            ).apply {
                putExtra("reservation", reservation)
            }

        ActivityScenario.launch<ReservationResultActivity>(intent)
    }

    @Test
    fun shouldDisplayTitleText() {
        onView(withId(R.id.tv_title))
            .check(matches(withText("해리포터")))
    }

    @Test
    fun shouldDisplayScreeningDateText() {
        onView(withId(R.id.tv_screening_date))
            .check(matches(withText("2025.04.30 12:00")))
    }

    @Test
    fun shouldDisplayTicketCount() {
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText("2명")))
    }

    @Test
    fun shouldDisplayTotalPrice() {
        onView(withId(R.id.tv_total_price))
            .check(matches(withText("26,000원")))
    }
}
