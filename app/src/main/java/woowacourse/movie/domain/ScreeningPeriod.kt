package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.helper.LocalDateHelper.toLocalDateFromDash
import woowacourse.movie.helper.LocalDateHelper.toLocalDateFromDot
import java.time.LocalDate

@Parcelize
data class ScreeningPeriod(
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
) : Parcelable {
    init {
        require(screeningEndDate.isAfter(screeningStartDate)) { ERROR_START_DATE_AFTER_END_DATE }
    }

    fun betweenDates(
        targetDate: LocalDate = LocalDate.now()
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()

        var standardDate = if (!isStart(targetDate)) screeningStartDate else targetDate

        while (!isEnd(targetDate) && !standardDate.isAfter(screeningEndDate)) {
            dates.add(standardDate)
            standardDate = standardDate.plusDays(1)
        }

        return dates.toList()
    }

    fun isEnd(targetDate: LocalDate): Boolean {
        return targetDate.isAfter(screeningEndDate)
    }

    fun isStart(targetDate: LocalDate): Boolean {
        return !targetDate.isBefore(screeningStartDate)
    }

    companion object {
        private const val ERROR_START_DATE_AFTER_END_DATE = "영화 시작 날짜가 영화 종료 날짜보다 후 입니다."

        fun ofDash(screeningStartDay: String, screeningEndDay: String): ScreeningPeriod {
            val screeningStartDate = screeningStartDay.toLocalDateFromDash()
            val screeningEndDate = screeningEndDay.toLocalDateFromDash()
            return ScreeningPeriod(screeningStartDate, screeningEndDate)
        }

        fun ofDot(screeningStartDay: String, screeningEndDay: String): ScreeningPeriod {
            val screeningStartDate = screeningStartDay.toLocalDateFromDot()
            val screeningEndDate = screeningEndDay.toLocalDateFromDot()
            return ScreeningPeriod(screeningStartDate, screeningEndDate)
        }
    }
}
