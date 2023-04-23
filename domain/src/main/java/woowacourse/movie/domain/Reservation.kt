package woowacourse.movie.domain

import java.time.LocalDateTime

data class Reservation(
    private val movie: Movie,
    val seats: List<Seat>,
    val screeningDateTime: LocalDateTime,
    val finalReservationFee: Money
) {
    val movieTitle = movie.title

    init {
        require(seats.size in MIN_PEOPLE_COUNT..MAX_PEOPLE_COUNT) { PEOPLE_COUNT_RANGE_ERROR }
        require(screeningDateTime.toLocalDate() in movie.screeningStartDate..movie.screeningEndDate) {
            SCREENING_DATE_RANGE_ERROR
        }
    }

    companion object {
        const val MIN_PEOPLE_COUNT = 1
        const val MAX_PEOPLE_COUNT = 20
        private const val PEOPLE_COUNT_RANGE_ERROR = "[ERROR] 예매 인원은 최소 1명 이상 최대 20명 이하여야 합니다."
        private const val SCREENING_DATE_RANGE_ERROR = "[ERROR] 예매할 날짜가 상영 기간이 아닙니다."
    }
}
