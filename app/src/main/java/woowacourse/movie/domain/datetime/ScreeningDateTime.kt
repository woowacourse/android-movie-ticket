package woowacourse.movie.domain.datetime

import java.io.Serializable
import java.time.LocalDateTime

class ScreeningDateTime(val time: LocalDateTime, private val screeningPeriod: ScreeningPeriod) :
    Serializable {
    init {
        validateDateTime()
    }

    private fun validateDateTime() {
        require(time.toLocalDate() in screeningPeriod.start..screeningPeriod.end) {
            SELECTED_SCREENING_DATE_TIME_ERROR.format(
                time.toString(),
                screeningPeriod.start.toString(),
                screeningPeriod.end.toString()
            )
        }
    }

    fun checkMovieDay(): Boolean = time.dayOfMonth in MOVIE_DAYS

    fun checkEarlyMorningLateNight(): Boolean {
        val hour = time.hour
        if (hour < EARLY_MORNING_STANDARD || hour > LATE_NIGHT_STANDARD) return true
        return false
    }

    companion object {
        private const val SELECTED_SCREENING_DATE_TIME_ERROR =
            "선택된 날짜(%s)가 영화 상영 기간(%s~%s) 범위에서 벗어났습니다."
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val EARLY_MORNING_STANDARD = 11
        private const val LATE_NIGHT_STANDARD = 20
    }
}
