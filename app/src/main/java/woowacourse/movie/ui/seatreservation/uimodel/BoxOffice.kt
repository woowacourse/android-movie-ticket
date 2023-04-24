package woowacourse.movie.ui.seatreservation.uimodel

import android.view.View
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.ABLE
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.DISABLE
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.MAX
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.REABLE

class BoxOffice {
    private val calculator: Calculator by lazy { Calculator.create() }
    var count: Int = 0

    fun calculate(view: View, seatLocation: Int): Int {
        val seatGrade = Seat.valueOf(seatLocation)

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

    sealed class SelectState() {
        object ABLE : SelectState()
        object DISABLE : SelectState()
        object REABLE : SelectState()
        object MAX : SelectState()
    }

    companion object {
        private const val DOUBLE = 2
        fun create() = BoxOffice()
    }
}
