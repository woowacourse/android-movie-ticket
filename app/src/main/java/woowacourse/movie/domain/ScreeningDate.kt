package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable {
    init {
        require(startDate <= endDate) { INVALID_DATE }
    }

    companion object {
        private const val INVALID_DATE = "상영 종료일은 상영 시작일 이후여야 합니다"
    }
}
