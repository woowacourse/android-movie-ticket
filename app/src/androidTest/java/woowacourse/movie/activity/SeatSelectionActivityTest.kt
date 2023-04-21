package woowacourse.movie.activity

import android.graphics.Color
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasTextColor
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.DateRange
import woowacourse.movie.domain.Image
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationDetail
import woowacourse.movie.view.mapper.MovieMapper.toView
import woowacourse.movie.view.mapper.ReservationDetailMapper.toView
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {
    private val movie = Movie(
        Image(0),
        "해리 포터",
        DateRange(
            LocalDate.of(2024, 3, 1),
            LocalDate.of(2024, 3, 31),
        ),
        153,
        "adsfasdfadsf",
    ).toView()

    private val reservationDetail = ReservationDetail(
        LocalDateTime.now(), 3
    ).toView()

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectionActivity>(
        SeatSelectionActivity.from(
            ApplicationProvider.getApplicationContext(), movie, reservationDetail
        )
    )

    @Test
    fun 좌석을_클릭하면_배경색이_노란색이_된다() {
        // given
        val seatText = "A2"

        // when
        onView(withChild(withChild(withText(seatText)))).perform(click())

        // then
        val seatColor = Color.parseColor("#FAFF00")
        onView(withChild(withChild(withText(seatText)))).check(
            matches(withBackgroundColor(seatColor))
        )
    }

    @Test
    fun 이미_선택된_좌석을_다시_클릭하면_다시_흰색이_된다() {
        // given
        val seatText = "A2"
        onView(withChild(withChild(withText(seatText)))).perform(click())

        // when
        onView(withChild(withChild(withText(seatText)))).perform(click())

        // then
        val seatColor = Color.WHITE
        onView(withChild(withChild(withText(seatText)))).check(
            matches(withBackgroundColor(seatColor))
        )
    }

    @Test
    fun 예매_인원이_3명이면_좌석을_3개까지_선택할_수_있다() {
        // given
        val seats = listOf("A2", "A3", "A4", "B1")

        // when
        for (seat in seats) {
            onView(withChild(withChild(withText(seat)))).perform(click())
        }

        // then
        val unselectableSeat = "B1"
        val seatColor = Color.WHITE
        onView(withChild(withChild(withText(unselectableSeat)))).check(
            matches(withBackgroundColor(seatColor))
        )
    }

    @Test
    fun 예매_인원과_선택_좌석_개수가_일치하면_확인_버튼이_활성화_된다() {
        // given
        val seats = listOf("A2", "A3", "A4")

        // when
        for (seat in seats) {
            onView(withChild(withChild(withText(seat)))).perform(click())
        }

        // then
        onView(withId(R.id.seat_selection_reserve_button)).check(
            matches(isEnabled())
        )
    }

    @Test
    fun 좌석을_선택하면_가격이_표시된다() {
        // given
        val seat = "A2"

        // when
        onView(withChild(withChild(withText(seat)))).perform(click())

        // then
        onView(withId(R.id.seat_selection_movie_price)).check(
            matches(not(withText("0원")))
        )
    }

    @Test
    fun 좌석_1_2행의_글자색은_보라색이다() {
        // given

        // when

        // then
        val seatPositions = listOf("A", "B")
        val seatColor = R.color.b_rank
        for (seatPosition in seatPositions) {
            onView(
                withText("%s1".format(seatPosition)),
            ).check(
                matches(
                    hasTextColor(seatColor)
                )
            )
        }
    }

    @Test
    fun 좌석_3_4행의_글자색은_초록색이다() {
        // given

        // when

        // then
        val seatPositions = listOf("C", "D")
        val seatColor = R.color.s_rank
        for (seatPosition in seatPositions) {
            onView(
                withText("%s1".format(seatPosition)),
            ).check(
                matches(
                    hasTextColor(seatColor)
                )
            )
        }
    }

    @Test
    fun 좌석_5행의_글자색은_파란색이다() {
        // given

        // when

        // then
        val seatPosition = "E"
        val seatColor = R.color.a_rank
        onView(
            withText("%s1".format(seatPosition)),
        ).check(
            matches(
                hasTextColor(seatColor)
            )
        )
    }
}
