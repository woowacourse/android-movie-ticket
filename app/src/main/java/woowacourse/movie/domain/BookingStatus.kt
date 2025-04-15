package woowacourse.movie.domain

data class BookingStatus(
    val movie: Movie,
    val isBooked: Boolean,
) {
    fun book(): BookingStatus {
        return if (!isBooked) BookingStatus(movie, true)
        else throw IllegalStateException(ERR_ALREADY_BOOKED)
    }

    fun cancel(): BookingStatus {
        return if (isBooked) BookingStatus(movie, false)
        else throw IllegalStateException(ERR_NOT_BOOKED)
    }

    companion object {
        private const val ERR_ALREADY_BOOKED = "이미 예매된 상태입니다."
        private const val ERR_NOT_BOOKED = "예매가 되어있지 않습니다"
    }
}
