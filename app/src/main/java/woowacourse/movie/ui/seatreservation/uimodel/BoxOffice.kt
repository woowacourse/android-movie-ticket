package woowacourse.movie.ui.seatreservation.uimodel

import android.view.View
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.ABLE
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.DISABLE
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.MAX
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.REABLE
import java.time.LocalDateTime

class BoxOffice private constructor(
    private val bookedDateTime: LocalDateTime,
) {
    private val calculator: Calculator by lazy { Calculator.create(bookedDateTime) }
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat> get() = _seats.toList()
    private var count: Int = 0

    fun calculate(view: View, seatLocation: Int): Int {
        val seatGrade = Seat.valueOf(seatLocation)

        _seats.add(seatGrade)
        getTotalSum(view, seatGrade)

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
        if (view.isSelected) return isReableState()
        return ABLE
    }

    private fun isMaxState(view: View): SelectState {
        if (view.isSelected) return isReableState()
        return MAX
    }

    private fun isOverState(view: View): SelectState {
        if (view.isSelected) return isReableState()
        count--
        return DISABLE
    }

    private fun isReableState(): REABLE {
        count -= DOUBLE
        return REABLE
    }

    private fun getTotalSum(view: View, seat: Seat) {
        val price = Money.from(seat.price)

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
