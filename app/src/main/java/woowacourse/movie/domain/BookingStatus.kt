package woowacourse.movie.domain

data class BookingStatus(
    val movie: Movie,
    val isBooked: Boolean,
) {
    fun book(): BookingStatus {
        if (isBooked == false) {
            return BookingStatus(this.movie, !isBooked)
        }
        throw IllegalStateException("이미 예매된 상태입니다.")
    }
}