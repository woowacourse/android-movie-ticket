package woowacourse.movie.domain

data class Reservation(
    val movie: Movie, val peopleCount: Int
) {
    init {
        require(peopleCount in MIN_PEOPLE_COUNT..MAX_PEOPLE_COUNT) { PEOPLE_COUNT_RANGE_ERROR }
    }

    companion object {
        const val MIN_PEOPLE_COUNT = 1
        const val MAX_PEOPLE_COUNT = 200
        private const val PEOPLE_COUNT_RANGE_ERROR = "[ERROR] 예매 인원은 최소 1명 이상 최대 200명 이하여야 합니다."
    }
}
