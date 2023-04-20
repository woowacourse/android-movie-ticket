package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Parcelize
class MovieUiModel(
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val posterResourceId: Int,
    val summary: String
) : Parcelable {
    fun getAllScreeningDates(): List<LocalDate> {
        val screeningDates = mutableListOf<LocalDate>()
        var screeningDate = screeningStartDate
        repeat(ChronoUnit.DAYS.between(screeningStartDate, screeningEndDate).toInt() + 1) {
            screeningDates.add(screeningDate)
            screeningDate = screeningDate.plusDays(1)
        }
        return screeningDates
    }
}
