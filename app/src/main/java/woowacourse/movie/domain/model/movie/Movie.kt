package woowacourse.movie.domain.model.movie

import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
) {
    init {
        require(title.isNotBlank()) { ERROR_TITLE_BLACK_MESSAGE }
        require(runningTime > 0) { ERROR_RUNNING_TIME_MESSAGE }
        require(isDateValid()) { ERROR_DATE_PERIOD_MESSAGE }
    }

    private fun isDateValid(): Boolean {
        return screeningStartDate.isBefore(screeningEndDate) ||
            screeningStartDate.isEqual(screeningEndDate)
    }

    fun isScreeningEnd(date: LocalDate): Boolean {
        return date.isAfter(screeningEndDate)
    }

    companion object {
        private const val ERROR_TITLE_BLACK_MESSAGE = "영화 제목은 비어있을 수 없다"
        private const val ERROR_RUNNING_TIME_MESSAGE = "영화 상영시간은 0보다 커야한다"
        private const val ERROR_DATE_PERIOD_MESSAGE = "시작 날짜는 종료 날짜보다 앞서야합니다 "
    }
}
