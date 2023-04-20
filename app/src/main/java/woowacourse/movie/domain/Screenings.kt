package woowacourse.movie.domain

class Screenings {

    private var _screenings: MutableMap<Screening, ReservationResult?> = mutableMapOf()

    val screenings: Map<Screening, ReservationResult?>
        get() = _screenings.toMap()

    fun addScreening(screening: Screening) {
        _screenings[screening] = null
        _screenings = _screenings.toSortedMap().toMutableMap()
    }

    fun reserve(screening: Screening, reservationResult: ReservationResult) {
        require(screening in _screenings) { SCREENING_NOT_EXISTS_ERROR }
        _screenings[screening] = reservationResult
    }

    companion object {
        private const val SCREENING_NOT_EXISTS_ERROR = "존재하지 않는 상영을 예매할 수 없습니다."
    }
}
