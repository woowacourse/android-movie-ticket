package woowacourse.movie

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
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.SeatSelectionInfo
import woowacourse.movie.reservation.ReservationActivity.Companion.SEAT_SELECTION_KEY
import woowacourse.movie.seatselection.ScreeningSeatSelectionActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatSelectionActivityTest {

    private val seatSelectionInfo = SeatSelectionInfo(
        movieName = "harrypoter",
        screeningTime = LocalDateTime.of(
            LocalDate.MIN,
            LocalTime.MIN
        ),
        seatCount = 2
    )

    private val intent = Intent(
        ApplicationProvider.getApplicationContext(),
        ScreeningSeatSelectionActivity::class.java
    ).apply {
        putExtra(SEAT_SELECTION_KEY, seatSelectionInfo)
    }

    @get:Rule
    val activityRule = ActivityScenarioRule<ScreeningSeatSelectionActivity>(intent)

    @Test
    fun `클릭한_좌석이_선택된_상태로_바뀐다`() {
        // given
        val seat = onView(withText("A1"))

        // when
        seat.perform(click())

        // then
        seat.check(matches(isSelected()))
    }

    @Test
    fun `선택한_좌석을_다시_클릭하면_선택되지_않은_상태로_바뀐다`() {
        // given
        val seat = onView(withText("A1"))
        seat.perform(click())

        // when
        seat.perform(click())

        // then
        seat.check(matches(not(isSelected())))
    }

    @Test
    fun `좌석을_모두_선택하면_클릭_가능한_버튼으로_바뀐다`() {
        // given
        onView(withText("A1")).perform(click())
        onView(withText("B1")).perform(click())

        // when
        val completeButton = onView(withId(R.id.seat_selection_complete_button))

        // then
        completeButton.check(matches(isEnabled()))
    }

    @Test
    fun `좌석을_모두_선택하지_않으면_클릭_불가능한_버튼으로_바뀐다`() {
        // given
        onView(withText("A1")).perform(click())

        // when
        val completeButton = onView(withId(R.id.seat_selection_complete_button))

        // then
        completeButton.check(matches(not(isEnabled())))
    }

    @Test
    fun `S등급의_좌석을_클릭하면_S좌석의_가격으로_갱신된다`() {
        // given
        val seat = onView(withText("C1"))

        // when
        seat.perform(click())
        val paymentAmount = onView(withId(R.id.seat_selection_payment_amount_text))

        // then
        paymentAmount.check(matches(withText("15000")))
    }

    @Test
    fun `A등급의_좌석을_클릭하면_A좌석의_가격으로_갱신된다`() {
        // given
        val seat = onView(withText("E1"))

        // when
        seat.perform(click())
        val paymentAmount = onView(withId(R.id.seat_selection_payment_amount_text))

        // then
        paymentAmount.check(matches(withText("12000")))
    }

    @Test
    fun `B등급의_좌석을_클릭하면_S좌석의_가격으로_갱신된다`() {
        // given
        val seat = onView(withText("B1"))

        // when
        seat.perform(click())
        val paymentAmount = onView(withId(R.id.seat_selection_payment_amount_text))

        // then
        paymentAmount.check(matches(withText("10000")))
    }

    @Test
    fun `여러_좌석을_클릭하면_두_좌석_가격의_합으로_갱신된다`() {
        // given
        val seatOfRateA = onView(withText("E1"))
        val seatOfRateB = onView(withText("A1"))

        // when
        seatOfRateA.perform(click())
        seatOfRateB.perform(click())
        val paymentAmount = onView(withId(R.id.seat_selection_payment_amount_text))

        // then
        paymentAmount.check(matches(withText("22000")))
    }
}
