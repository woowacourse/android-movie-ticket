package woowacourse.movie.presentation.bookingsummary

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.MovieTicket
import woowacourse.movie.presentation.booking.BookingActivity
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class BookingSummaryActivityTest {
    @Before
    fun setUp() {
        val ticket = MovieTicket(
            "Test",
            LocalDateTime.of(2025, 12, 31, 12, 0),
            2,
            27000
        )

        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            BookingSummaryActivity::class.java
        ).apply {
            putExtra("Ticket", ticket)
        }

        ActivityScenario.launch<BookingActivity>(intent)
    }

    @Test
    fun 영화_제목이_출력된다() {
        onView(withId(R.id.textview_title))
            .check(matches(withText("Test")))
    }

    @Test
    fun 상영날짜와_시간이_출력된다() {
        onView(withId(R.id.textview_screeningdatetime))
            .check(matches(withText("2025.12.31 12:00")))
    }

    @Test
    fun 예매_인원이_출력된다() {
        onView(withId(R.id.textview_headcount))
            .check(matches(withText("일반 2명")))
    }

    @Test
    fun 총_결제금액이_출력된다() {
        onView(withId(R.id.textview_amount))
            .check(matches(withText("27,000원 (현장 결제)")))
    }
}