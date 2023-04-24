package woowacourse.movie.ui.seatreservation.uimodel

class Calculator {
    var totalAmount: Int = 0
        private set

    fun plus(selectSeatPrice: Money) {
        totalAmount += selectSeatPrice.value
    }

    fun minus(selectSeatPrice: Money) {
        totalAmount -= selectSeatPrice.value
    }

    companion object {
        fun create() = Calculator()
    }
}
