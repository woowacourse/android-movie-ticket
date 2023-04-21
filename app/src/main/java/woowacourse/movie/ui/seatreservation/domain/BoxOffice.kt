package woowacourse.movie.ui.seatreservation.domain

import android.view.View

class BoxOffice {
    private val calculator: Calculator by lazy { Calculator.create() }

    fun calculate(view: View, seatLocation: Int): Int {
        val seatGrade = Seat.valueOf(seatLocation)

        getTotalSum(view, seatGrade)

        return calculator.totalAmount
    }

    private fun getTotalSum(view: View, seat: Seat) {
        val price = Money.from(seat.price)

        when (view.isSelected) {
            true -> calculator.minus(price)
            false -> calculator.plus(price)
        }
    }

    companion object {

        fun create() = BoxOffice()
    }
}
