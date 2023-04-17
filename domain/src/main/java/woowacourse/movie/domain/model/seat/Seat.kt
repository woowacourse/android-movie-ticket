package woowacourse.movie.domain.model.seat

class Seat(private val row: Int) {
    fun getClass(): SeatClass = SeatClass.get(row)
}
