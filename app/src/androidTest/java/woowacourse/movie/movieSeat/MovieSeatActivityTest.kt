package woowacourse.movie.movieSeat

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import movie.data.TicketCount
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.movieSeat.MovieSeatActivity.Companion.KEY_MOVIE_DETAIL
import woowacourse.movie.uimodel.MovieDetailUi
import java.time.LocalDate
import java.time.LocalTime

internal class MovieSeatActivityTest {
    // 할인은 적용되지 않는다고 가정한다
    private val selectedDate = LocalDate.of(2023, 4, 1)
    private val selectedTime = LocalTime.of(14, 0)

    private val movieDetailsUi = MovieDetailUi(
        title = "범죄도시3",
        count = TicketCount(2),
        date = selectedDate,
        time = selectedTime,
    )

    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), MovieSeatActivity::class.java).apply {
            putExtra(KEY_MOVIE_DETAIL, movieDetailsUi)
        }

    @get:Rule
    internal val activityRule = ActivityScenarioRule<MovieSeatActivity>(intent)

    @Test
    fun 영화제목은_범죄도시3이다() {
        onView(withId(R.id.seat_movie_title))
            .check(matches(withText("범죄도시3")))
    }

    @Test
    fun 선택되지_않은_좌석인_A1을_클릭하면_선택된다() {
        onView(withText("A1"))
            .perform(click())
            .check(matches(isSelected()))
    }

    @Test
    fun 선택된_좌석인_A1을_클릭하면_선택해제된다() {
        // given
        onView(withText("A1"))
            .perform(click())

        // when
        onView(withText("A1"))
            .perform(click())

        // then
        onView(withText("A1"))
            .check(matches(isNotSelected()))
    }

    @Test
    fun 선택되지_않은_좌석인_A1을_클릭하면_금액이_10_000원으로_변경된다() {
        // when
        onView(withText("A1"))
            .perform(click())

        // then
        onView(withId(R.id.seat_ticket_price))
            .check(matches(withText("10000원")))
    }

    @Test
    fun 선택되지_않은_좌석인_A1과_C1_을_클릭하면_금액이_25_000원으로_변경된다() {
        // when
        onView(withText("A1"))
            .perform(click())
        onView(withText("C1"))
            .perform(click())

        // then
        onView(withId(R.id.seat_ticket_price))
            .check(matches(withText("25000원")))
    }

    @Test
    fun 선택되지_않은_A1과_C1_을_클릭하고_D1을_클릭해도_선택되지_않는다() {
        // given
        onView(withText("A1"))
            .perform(click())
        onView(withText("C1"))
            .perform(click())

        // when
        onView(withText("D1"))
            .perform(click())

        // then
        onView(withText("D1"))
            .check(matches(isNotSelected()))
    }

    @Test
    fun 티켓의_수가_2장일_때_한_좌석만_선택되면_확인_버튼을_클릭할_수_없다() {
        // given
        onView(withText("A1"))
            .perform(click())

        // then
        onView(withId(R.id.seat_next_button))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun 티켓의_수가_2장일_때_두_좌석이_선택되면_확인_버튼을_클릭할_수_있다() {
        // given
        onView(withText("A1"))
            .perform(click())
        onView(withText("C1"))
            .perform(click())

        // then
        onView(withId(R.id.seat_next_button))
            .check(matches(isEnabled()))
    }

    @Test
    fun 티켓의_수가_2장이고_두_자리가_선택되어을_때_확인버튼_클릭시_다이얼로그가_뜬다() {
        // given
        onView(withText("A1"))
            .perform(click())
        onView(withText("C1"))
            .perform(click())

        // when
        onView(withId(R.id.seat_next_button))
            .perform(click())

        // then
        onView(withText("정말 예매하시겠습니까?"))
            .inRoot(isDialog())
    }
}
