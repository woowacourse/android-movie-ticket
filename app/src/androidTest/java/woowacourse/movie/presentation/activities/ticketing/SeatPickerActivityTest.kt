package woowacourse.movie.presentation.activities.ticketing

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.presentation.activities.movielist.MovieListActivity.Companion.MOVIE_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_DATE_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.MOVIE_TIME_KEY
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity.Companion.TICKET_KEY
import woowacourse.movie.presentation.model.MovieDate
import woowacourse.movie.presentation.model.MovieTime
import woowacourse.movie.presentation.model.Ticket
import woowacourse.movie.presentation.model.movieitem.Movie
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
@LargeTest
internal class SeatPickerActivityTest {
    private val movie: Movie = Movie(
        "테스트 영화",
        startDate = LocalDate.of(2023, 4, 1),
        endDate = LocalDate.of(2023, 4, 30),
        152,
        "테스트 영화 소개",
        R.drawable.img_sample_movie_thumbnail1,
    )

    // 무비 데이가 아니므로 할인 정책 적용 안 된다고 가정한다
    private val selectedMovieDate: MovieDate = MovieDate(2023, 4, 1)
    private val selectedMovieTime: MovieTime = MovieTime(12, 0)
    private val movieTicket: Ticket = Ticket(2)

    private val initialSeats = listOf(
        "A1", "A2", "A3", "A4",
        "B1", "B2", "B3", "B4",
        "C1", "C2", "C3", "C4",
        "D1", "D2", "D3", "D4",
        "E1", "E2", "E3", "E4",
    )

    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), SeatPickerActivity::class.java).apply {
            putExtra(MOVIE_KEY, movie)
            putExtra(TICKET_KEY, movieTicket)
            putExtra(MOVIE_DATE_KEY, selectedMovieDate)
            putExtra(MOVIE_TIME_KEY, selectedMovieTime)
        }

    @get:Rule
    internal val activityRule = ActivityScenarioRule<SeatPickerActivity>(intent)

    @Test
    internal fun 영화_제목이_보여진다() {
        onView(withId(R.id.movie_title_tv))
            .check(matches(withText("테스트 영화")))
    }

    @Test
    internal fun 사용자가_빈_좌석을_선택하면_좌석이_선택된다() {
        // given: 빈 좌석이 선택되어 있지 않은 상태
        val seat = initialSeats.first()
        onView(withText(seat))
            .check(matches(not(isSelected())))

        // when: 사용자가 빈 좌석을 선택
        onView(withText(seat))
            .perform(click())

        // then: 좌석이 선택된다
        onView(withText(seat))
            .check(matches(isSelected()))
    }

    @Test
    internal fun 사용자가_선택된_좌석을_선택하면_좌석_선택이_해제된다() {
        // given: 좌석이 선택되어 있는 상태
        val seat = initialSeats.first()
        onView(withText(seat))
            .perform(click())

        // when: 사용자가 선택된 좌석을 재선택
        onView(withText(seat))
            .perform(click())

        // then: 좌석 선택이 해제된다
        onView(withText(seat))
            .check(matches(not(isSelected())))
    }

    @Test
    internal fun 사용자가_티켓_개수만큼_좌석을_선택하면_버튼이_활성화된다() {
        // given: 선택할 좌석이 주어진다
        val pickedSeat = initialSeats.slice(0..movieTicket.count)

        // when: 사용자가 티켓 개수만큼 좌석을 선택한다
        pickedSeat.forEach { seatName ->
            onView(withText(seatName))
                .perform(click())
        }

        // then: 버튼이 활성화된다
        onView(withId(R.id.done_btn))
            .check(matches(isEnabled()))
    }

    @Test
    internal fun 버튼이_활성화된_상태에서_좌석_선택을_해제하면_버튼이_비활성화된다() {
        // given: 좌석을 모두 선택하여 버튼이 활성화된 상태
        val pickedSeats = initialSeats.slice(0..movieTicket.count)
        pickedSeats.forEach { seatName ->
            onView(withText(seatName))
                .perform(click())
        }

        // when: 사용자가 좌석 선택을 해제한다
        onView(withText(pickedSeats.first()))
            .perform(click())

        // then: 버튼이 비활성화된다
        onView(withId(R.id.done_btn))
            .check(matches(not(isEnabled())))
    }

    @Test
    internal fun 사용자가_티켓_개수만큼_좌석을_선택하면_더_이상_좌석_선택이_불가능하다() {
        // given: 전체 좌석과 선택할 좌석이 주어진다
        val pickedSeats = initialSeats.slice(0 until movieTicket.count)
        val unpickedSeats = initialSeats - pickedSeats.toSet()

        // when: 선택할 좌석을 모두 선택하고,
        pickedSeats.forEach { seat ->
            onView(withText(seat))
                .perform(click())
        }

        // when: 나머지 좌석을 선택한다
        unpickedSeats.forEach { unpickedSeat ->
            onView(withText(unpickedSeat))
                .perform(click())
        }

        // then: 티켓 수를 초과한 이후에는 나머지 좌석이 선택되지 않는다
        unpickedSeats.forEach { unpickedSeat ->
            onView(withText(unpickedSeat))
                .check(matches(not(isSelected())))
        }
    }

    @Test
    internal fun 사용자가_좌석을_선택하면_총_결제_금액이_증가한다() {
        // given: 선택할 좌석이 주어진다
        val pickedSeats = listOf(initialSeats.first(), initialSeats.last())

        // when: 사용자가 좌석을 선택한다
        pickedSeats.forEach { seat ->
            onView(withText(seat))
                .perform(click())
        }

        // then: 총 결제 금액이 보여진다
        val totalPriceText = "22,000원"
        onView(withId(R.id.total_price_tv))
            .check(matches(withText(totalPriceText)))
    }
}
