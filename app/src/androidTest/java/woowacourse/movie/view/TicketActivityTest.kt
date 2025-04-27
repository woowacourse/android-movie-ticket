package woowacourse.movie.view

import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.view.model.ResourceMapper
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.model.SeatsData
import woowacourse.movie.view.model.TicketData
import woowacourse.movie.view.ticket.TicketActivity
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
            seatsLength = 2,
            totalSeatsPrice = 20000,
            seatsCodes = listOf("A1", "A2"),
        )

    // 2명 티켓 데이터
    private val ticketData =
        TicketData(
            screeningData = screeningData,
            ticketCount = 2,
            showtime = LocalDateTime.of(LocalDate.of(2025, 4, 20), LocalTime.of(13, 0)),
            seatsData = seatsData,
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
        onView(withId(R.id.tv_ticket_movie_title))
            .check(matches(isDisplayed()))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `상영_일시가_표시된다`() {
        onView(withId(R.id.tv_ticket_screening_date))
            .check(matches(isDisplayed()))
            .check(matches(withSubstring("2025.4.20")))
    }

    @Test
    fun `취소_가능_시간_정보가_표시된다`() {
        onView(withId(R.id.tv_ticket_cancel_time_info))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `티켓_수량_및_좌석_정보가_표시된다`() {
        onView(withId(R.id.tv_ticket_count))
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("일반 2명"))))
            .check(matches(withText(containsString("A1, A2"))))
    }

    @Test
    fun `티켓_가격이_표시된다`() {
        onView(withId(R.id.tv_ticket_price))
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("20,000원"))))
    }

    @Test
    fun `화면_회전시_데이터가_유지된다`() {
        // 기본 정보 확인
        onView(withId(R.id.tv_ticket_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText(containsString("A1, A2"))))

        // 화면 회전
        rotateToLandscape()

        // 회전 후에도 정보 유지 확인
        onView(withId(R.id.tv_ticket_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
        onView(withId(R.id.tv_ticket_count))
            .check(matches(withText(containsString("A1, A2"))))
        onView(withId(R.id.tv_ticket_price))
            .check(matches(withText(containsString("20,000원"))))
    }

    @Test
    fun `가로_모드에서_모든_정보가_표시된다`() {
        // 화면 회전
        rotateToLandscape()

        // 가로 모드에서 정보 표시 확인
        onView(withId(R.id.tv_ticket_movie_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_ticket_screening_date))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_ticket_count))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tv_ticket_price))
            .check(matches(isDisplayed()))
    }
}
