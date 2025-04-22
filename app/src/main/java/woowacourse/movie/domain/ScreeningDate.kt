package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ScreeningDate(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable {
    init {
        require(startDate <= endDate) { INVALID_DATE }
    }

    fun reservableDates(currentDate: LocalDate): List<LocalDate> {
        val reservableDate = selectLaterOf(startDate, currentDate)
        return getDatesBetween(reservableDate)
    }

    private fun selectLaterOf(
        movieStartDate: LocalDate,
        currentDate: LocalDate,
    ): LocalDate {
        return if (movieStartDate < currentDate) {
            currentDate
        } else {
            movieStartDate
        }
    }

    private fun getDatesBetween(startDate: LocalDate): List<LocalDate> {
        val dayDiff = ChronoUnit.DAYS.between(startDate, endDate)

        return (0..dayDiff).map { startDate.plusDays(it) }
    }

    companion object {
        private const val INVALID_DATE = "상영 종료일은 상영 시작일 이후여야 합니다"
    }
}
