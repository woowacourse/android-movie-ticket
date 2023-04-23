package woowacourse.movie.domain

class MovieHouse(val id: Long, val seats: Set<Seat>) {

    init {
        require(seatsDoNotOverlap()) { OVERLAP_SEAT_ERROR }
    }

    private fun seatsDoNotOverlap(): Boolean =
        seats.map { it.point }.distinct() == seats.map { it.point }

    override fun equals(other: Any?): Boolean = if (other is MovieHouse) id == other.id else false

    override fun hashCode(): Int = id.hashCode()

    companion object {
        private const val OVERLAP_SEAT_ERROR = "영화관 내의 좌석들의 위치가 겹칠 수 없습니다."
    }
}
