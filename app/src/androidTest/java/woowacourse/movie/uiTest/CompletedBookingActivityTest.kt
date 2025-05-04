package woowacourse.movie.uiTest

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.completedbooking.CompletedBookingActivity
import woowacourse.movie.detailbooking.DetailBookingActivity
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.domain.Ticket
import java.time.LocalDateTime

class CompletedBookingActivityTest {
    @Before
    fun setUp() {
        val ticket =
            Ticket(
                ReservationInfo(
                    "해리 포터와 마법사의 돌",
                    LocalDateTime.of(2025, 4, 25, 11, 0),
                    2,
                ),
                setOf("A1", "B1"),
                24_000,
            )

        val intent = CompletedBookingActivity.newIntent(ApplicationProvider.getApplicationContext(), ticket)

        ActivityScenario.launch<DetailBookingActivity>(intent)
    }

    @Test
    @DisplayName("영화 취소 안내 메시지가 보여야 한다")
    fun ticketCancelInfoIsDisplayed() {
        onView(withId(R.id.ticket_cancel_info_Text))
            .check(matches(withText("영화 상영 시작 15분 전까지\n취소가 가능합니다.")))
    }

    @Test
    @DisplayName("예매한 영화 제목이 보여야 한다")
    fun ticketMovieTitleIsDisplayed() {
        onView(withId(R.id.ticket_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    @DisplayName("예매한 영화 날짜와 시간이 보여야 한다")
    fun ticketDateTimeIsDisplayed() {
        onView(withId(R.id.ticket_movie_datetime))
            .check(matches(withText("2025.4.25. 11:00")))
    }

    @Test
    @DisplayName("예매한 영화 인원 수가 보여야 한다")
    fun moviePersonnelIs2() {
        onView(withId(R.id.ticket_movie_personnel))
            .check(matches(withText("일반 2명 | A1, B1")))
    }

    @Test
    @DisplayName("예매한 영화 티켓 가격이 보여야 한다")
    fun ticketTotalPriceIsDisplayed() {
        onView(withId(R.id.ticket_total_price))
            .check(matches(withText("24,000원 (현장 결제)")))
    }
}
