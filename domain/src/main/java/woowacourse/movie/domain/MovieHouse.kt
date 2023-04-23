package woowacourse.movie.domain

class MovieHouse(val id: Long, val seats: Set<Seat>) {

    init {
        require(seatsDoNotOverlap()) { OVERLAP_SEAT_ERROR }
    }

    private fun seatsDoNotOverlap(): Boolean =
        seats.map { it.point }.distinct() == seats.map { it.point }

    fun containsSeatOn(point: Point): Boolean = seats.any { it.point == point }

    fun getFee(seatPoint: Point): Money {
        val seat = seats.find { it.point == seatPoint }
        requireNotNull(seat) { NOT_EXIST_SEAT_ERROR.format(seatPoint.row, seatPoint.column) }
        return seat.seatClass.seatFee
    }

    override fun equals(other: Any?): Boolean = if (other is MovieHouse) id == other.id else false

    override fun hashCode(): Int = id.hashCode()

    companion object {
        private const val OVERLAP_SEAT_ERROR = "영화관 내의 좌석들의 위치가 겹칠 수 없습니다."
        private const val NOT_EXIST_SEAT_ERROR = "%d행 %d열에는 좌석이 존재하지 않습니다."
    }
}
