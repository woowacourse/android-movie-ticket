package woowacourse.movie.model

class ReservationAmount(val amount: Int) {
    operator fun plus(value: ReservationAmount): ReservationAmount {
        return ReservationAmount(amount + value.amount)
    }

    operator fun minus(value: ReservationAmount): ReservationAmount {
        return ReservationAmount(amount - value.amount)
    }
}
