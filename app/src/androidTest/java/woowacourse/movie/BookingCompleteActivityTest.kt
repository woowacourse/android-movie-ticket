package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import java.time.LocalDate
import java.time.LocalTime

class BookingCompleteActivityTest {
    private lateinit var scenario: ActivityScenario<BookingCompleteActivity>

    @Before
    fun setUp() {
        val intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                BookingCompleteActivity::class.java
            ).apply {
                putExtra("bookingResult", mockBookingResult().toUiModel())
            }

        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun `화면에_영화_제목이_표시된다`() {
        onView(withId(R.id.tv_complete_title)).check(
            matches(
                allOf(
                    withText("해리 포터와 마법사의 돌"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_상영일이_표시된다`() {
        onView(withId(R.id.tv_complete_screening_date)).check(
            matches(
                allOf(
                    withText("2028.10.13"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_상영시간이_표시된다`() {
        onView(withId(R.id.tv_complete_screening_time)).check(
            matches(
                allOf(
                    withText("11:00"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_예매인원이_표시된다`() {
        onView(withId(R.id.tv_head_count)).check(
            matches(
                allOf(
                    withText("일반 2명"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_예매좌석이_표시된다`() {
        onView(withId(R.id.tv_seats)).check(
            matches(
                allOf(
                    withText("A1,C1"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `화면에_선택한_영화_결제금액이_표시된다`() {
        onView(withId(R.id.tv_booking_amount)).check(
            matches(
                allOf(
                    withText("25,000원 (현장 결제)"),
                    isDisplayed(),
                ),
            ),
        )
    }

    private fun mockBookingResult(): Ticket {
        return Ticket(
            title = "해리 포터와 마법사의 돌",
            headCount = HeadCount(2),
            selectedDate = LocalDate.of(2028, 10, 13),
            selectedTime = LocalTime.of(11, 0),
            seats = Seats(listOf(Seat("A1", true), Seat("C1", true)))
        )
    }
}
