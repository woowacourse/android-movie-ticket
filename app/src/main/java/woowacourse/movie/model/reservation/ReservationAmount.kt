package woowacourse.movie.model.reservation

class ReservationAmount(val amount: Int = INITIALIZE_AMOUNT) {
    operator fun plus(value: ReservationAmount): ReservationAmount {
        return ReservationAmount(amount + value.amount)
    }

    operator fun minus(value: ReservationAmount): ReservationAmount {
        return ReservationAmount(amount - value.amount)
    }

    companion object {
        private const val INITIALIZE_AMOUNT = 0
    }
}
