package woowacourse.movie.view

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.ticketing.BookingSeat
import woowacourse.movie.view.state.TicketingResult
import java.time.LocalDate
import java.time.LocalTime

class TicketingResultActivityTest {
    @get:Rule
    val activityScenario: ActivityScenarioRule<TicketingResultActivity> =
        ActivityScenarioRule<TicketingResultActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                TicketingResultActivity::class.java,
            ).apply {
                putExtra(
                    "ticketing_result",
                    TicketingResult(
                        movieTitle = "해리 포터와 마법사의 돌",
                        numberOfTickets = 5,
                        date = LocalDate.of(2024, 3, 1),
                        time = LocalTime.of(15, 0),
                        seats =
                            listOf(
                                BookingSeat(0, 0, SeatClass.B),
                                BookingSeat(1, 0, SeatClass.B),
                                BookingSeat(2, 0, SeatClass.S),
                                BookingSeat(3, 0, SeatClass.S),
                                BookingSeat(4, 0, SeatClass.A),
                            ),
                        price = 62000,
                    ),
                )
            },
        )

    @Test
    fun `예매_결과_데이터를_보여준다`() {
        onView(withId(R.id.tv_guide)).check(matches(withText("영화 상영 시작 시간 15분 전까지\n취소가 가능합니다.")))
        onView(withId(R.id.tv_movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
        onView(withId(R.id.tv_movie_date)).check(matches(withText("2024.3.1 15:00")))
        onView(withId(R.id.tv_number_of_people_seats)).check(matches(withText("일반 5명 | A1, B1, C1, D1, E1")))
        onView(withId(R.id.tv_price)).check(matches(withText("62,000원 (현장 결제)")))
    }
}
