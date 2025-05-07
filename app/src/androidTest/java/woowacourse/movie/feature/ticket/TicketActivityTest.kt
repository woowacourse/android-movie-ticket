package woowacourse.movie.feature.ticket

import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.extension.ResourceMapper
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.seatSelect.SeatIndexData
import woowacourse.movie.feature.seatSelect.SeatsData
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TicketActivityTest {
    private val screeningData =
        ScreeningData(
            title = "해리 포터와 마법사의 돌",
            startDate = LocalDate.of(2025, 4, 16),
            endDate = LocalDate.of(2025, 4, 21),
            movieId = "HarryPotter1",
            runningTime = 152,
            poster = ResourceMapper.movieIdToPosterImageResource("HarryPotter1"),
        )

    private val seatsData =
        SeatsData(
            listOf(SeatIndexData(0, 0), SeatIndexData(0, 1)),
        )

    // 2명 티켓 데이터
    private val ticketData =
        TicketData(
            screeningData = screeningData,
            ticketCount = 2,
            showtime = LocalDateTime.of(LocalDate.of(2025, 4, 20), LocalTime.of(13, 0)),
            seatsData = seatsData,
            totalTicketPrice = 20000,
        )

    @get:Rule
    val activityRule =
        ActivityScenarioRule<TicketActivity>(
            TicketActivity.newIntent(
                ApplicationProvider.getApplicationContext(),
                ticketData,
            ),
        )

    private fun rotateToLandscape() {
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1500)
    }

    @Test
    fun `영화_제목이_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `상영_일시가_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_screening_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withSubstring("2025.4.20")))
    }

    @Test
    fun `취소_가능_시간_정보가_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_cancel_time_info))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `티켓_수량_및_좌석_정보가_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_count))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("일반 2명"))))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_seat_code))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("A1, A2"))))
    }

    @Test
    fun `티켓_가격이_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("20,000원"))))
    }

    @Test
    fun `화면_회전시_데이터가_유지된다`() {
        // 기본 정보 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("해리 포터와 마법사의 돌")))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_seat_code))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("A1, A2"))))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("20,000원"))))

        // 화면 회전
        rotateToLandscape()

        // 회전 후에도 정보 유지 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("해리 포터와 마법사의 돌")))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_seat_code))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("A1, A2"))))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("20,000원"))))
    }

    @Test
    fun `가로_모드에서_모든_정보가_표시된다`() {
        // 화면 회전
        rotateToLandscape()

        // 가로 모드에서 정보 표시 확인
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_movie_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_screening_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_count))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_ticket_price))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
