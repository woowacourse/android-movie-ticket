package woowacourse.movie.domain.datetime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
class ScreeningDateTime(val value: LocalDateTime, private val screeningPeriod: ScreeningPeriod) :
    Parcelable {
    init {
        validateDateTime()
    }

    private fun validateDateTime() {
        require(value.toLocalDate() in screeningPeriod.start..screeningPeriod.end) {
            SELECTED_SCREENING_DATE_TIME_ERROR.format(
                value.toString(),
                screeningPeriod.start.toString() + screeningPeriod.end.toString()
            )
        }
    }

    fun checkMovieDay(): Boolean = value.dayOfMonth in MOVIE_DAYS

    fun checkEarlyMorningLateNight(): Boolean {
        val hour = value.hour
        if (hour < EARLY_MORNING_STANDARD || hour > LATE_NIGHT_STANDARD) return true
        return false
    }

    companion object {
        private const val SELECTED_SCREENING_DATE_TIME_ERROR =
            "선택된 날짜(%s)가 영화 상영 기간(%s) 범위에서 벗어났습니다."
        private val MOVIE_DAYS = listOf(10, 20, 30)
        private const val EARLY_MORNING_STANDARD = 11
        private const val LATE_NIGHT_STANDARD = 20
    }
}
