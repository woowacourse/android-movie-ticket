package woowacourse.movie

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
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import woowacourse.movie.model.DefaultPricingPolicy
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.ui.view.booking.BookingActivity
import woowacourse.movie.ui.view.booking.BookingSummaryActivity
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class BookingSummaryActivityTest {
    @Before
    fun setUp() {
        val ticket =
            MovieTicket(
                "Test",
                LocalDateTime.of(2025, 4, 18, 12, 0),
                2,
                DefaultPricingPolicy(),
            )

        val intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                BookingSummaryActivity::class.java,
            ).apply {
                putExtra("Ticket", ticket)
            }

        ActivityScenario.launch<BookingActivity>(intent)
    }

    @DisplayName("영화 제목이 출력된다")
    @Test
    fun titleTest() {
        onView(withId(R.id.title))
            .check(matches(withText("Test")))
    }

    @DisplayName("상영날짜와 시간이 출력된다")
    @Test
    fun screeningDateTimeTest() {
        onView(withId(R.id.screeningDateTime))
            .check(matches(withText("2025.04.18 12:00")))
    }

    @DisplayName("예매 인원이 출력된다")
    @Test
    fun headCountTest() {
        onView(withId(R.id.headCount))
            .check(matches(withText("일반 2명")))
    }

    @DisplayName("총 결제 금액이 출력된다")
    @Test
    fun amountTest() {
        onView(withId(R.id.amount))
            .check(matches(withText("26,000원 (현장 결제)")))
    }
}
