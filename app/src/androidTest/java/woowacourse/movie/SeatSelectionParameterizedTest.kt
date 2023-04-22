package woowacourse.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRate
import domain.seat.SeatRow
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import woowacourse.movie.SeatSelectionActivityTestUtil.SeatSelectionInfo
import woowacourse.movie.SeatSelectionActivityTestUtil.toText

@RunWith(Parameterized::class)
class SeatSelectionParameterizedTest(private val seatData: ScreeningSeat) {
    @Test
    fun `특정_등급의_좌석을_선택하면_해당_좌석_등급의_금액으로_갱신된다`() {
        SeatSelectionActivityTestUtil.startTest(SeatSelectionInfo(seatCount = 1))
        // given
        val seat = Espresso.onView(ViewMatchers.withText(seatData.toText()))

        // when
        seat.perform(ViewActions.click())
        val paymentAmount =
            Espresso.onView(ViewMatchers.withId(R.id.seat_selection_payment_amount_text))

        // then
        paymentAmount.check(ViewAssertions.matches(ViewMatchers.withText(seatData.paymentAmount.value.toString())))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<ScreeningSeat> = listOf(
            ScreeningSeat(SeatRow.A, SeatColumn.FIRST, SeatRate.B),
            ScreeningSeat(SeatRow.C, SeatColumn.FIRST, SeatRate.S),
            ScreeningSeat(SeatRow.E, SeatColumn.FIRST, SeatRate.A)
        )
    }
}
