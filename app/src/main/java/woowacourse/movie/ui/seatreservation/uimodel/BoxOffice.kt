package woowacourse.movie.ui.seatreservation.uimodel

import android.view.View
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.ABLE
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.DISABLE
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.MAX
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.RETRY
import java.time.LocalDateTime

class BoxOffice private constructor(
    private val bookedDateTime: LocalDateTime,
) {
    private val calculator: Calculator by lazy { Calculator.create(bookedDateTime) }
    private val seat: Seat by lazy { Seat() }

    private val _seats: MutableList<String> = mutableListOf()
    val seats2: List<String> get() = _seats.toList()

    private var count: Int = 0

    fun calculate(view: View, seatLocation: Int): Int {
        val seatPrice = seat.getSeatPrice(seatLocation)
        val seat = seat.getSeatLocation(seatLocation)

        _seats.add(seat)
        getTotalSum(view, seatPrice)

        return calculator.totalAmount
    }

    fun select(ticketCount: Int, view: View): SelectState {
        count++

        return when {
            count == ticketCount -> isMaxState(view)
            count > ticketCount -> isOverState(view)
            else -> isNonMaxState(view)
        }
    }

    private fun isNonMaxState(view: View): SelectState {
        if (view.isSelected) return isRetryState()
        return ABLE
    }

    private fun isMaxState(view: View): SelectState {
        if (view.isSelected) return isRetryState()
        return MAX
    }

    private fun isOverState(view: View): SelectState {
        if (view.isSelected) return isRetryState()
        count--
        return DISABLE
    }

    private fun isRetryState(): RETRY {
        count -= DOUBLE
        return RETRY
    }

    private fun getTotalSum(view: View, seatGrade: SeatGrade) {
        val price = Money.from(seatGrade.price)

        when (view.isSelected) {
            true -> calculator.minus(price)
            false -> calculator.plus(price)
        }
    }

    companion object {
        private const val DOUBLE = 2
        fun create(bookedDateTime: LocalDateTime) = BoxOffice(bookedDateTime)
    }
}
