package movie.domain.datetime

import java.time.LocalDateTime

class ScreeningDateTime(
    val dateTime: LocalDateTime,
    val screeningPeriod: ScreeningPeriod
) {
    init {
        validateDateTime()
    }

    private fun validateDateTime() {
        require(dateTime.toLocalDate() in screeningPeriod.start..screeningPeriod.end) { SELECTED_SCREENING_DATE_TIME_ERROR }
    }

    fun checkMovieDay(): Boolean {
        if (dateTime.dayOfMonth in MOVIE_DAYS) return true
        return false
    }

    fun checkEarlyMorningLateNight(): Boolean {
        val hour = dateTime.hour
        if (hour < EARLY_MORNING_STANDARD || hour > LATE_NIGHT_STANDARD) return true
        return false
    }

    companion object {
        private const val SELECTED_SCREENING_DATE_TIME_ERROR = "선택된 날짜가 영화 상영 기간 범위에서 벗어났습니다."
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val EARLY_MORNING_STANDARD = 11
        private const val LATE_NIGHT_STANDARD = 20
    }
}
