package woowacourse.movie.domain

class MovieDay : DiscountPolicy {
    override fun discount(reservationDetail: ReservationDetail): ReservationDetail {
        if (reservationDetail.date.dayOfMonth in MOVIE_DAY) {
            val discountPrice = Price((reservationDetail.price.value * 0.9f).toInt())
            return ReservationDetail(
                reservationDetail.date,
                reservationDetail.peopleCount,
                discountPrice,
            )
        }
        return reservationDetail
    }

    companion object {
        private val MOVIE_DAY = listOf(10, 20, 30)
    }
}
