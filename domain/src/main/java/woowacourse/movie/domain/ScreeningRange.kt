package woowacourse.movie.domain

import java.time.LocalDate

data class ScreeningRange(val startDate: LocalDate, val endDate: LocalDate) {

    init {
        require(startDate <= endDate) { START_DATE_AFTER_END_DATE_ERROR }
    }

    companion object {
        private const val START_DATE_AFTER_END_DATE_ERROR = "시작 날짜는 마지막 날짜 이후일 수 없습니다."
    }
}
