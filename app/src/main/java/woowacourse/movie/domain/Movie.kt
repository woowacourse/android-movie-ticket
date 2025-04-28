package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
) {
    init {
        require(startDate <= endDate) { ERROR_INVALID_SCREENING_DATE }
    }

    companion object {
        private const val ERROR_INVALID_SCREENING_DATE = "영화 상영 종료일은 상영 시작일 이후여야 합니다."
    }
}
