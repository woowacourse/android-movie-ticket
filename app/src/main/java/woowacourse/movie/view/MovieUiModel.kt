package woowacourse.movie.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Minute
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.Period


fun Movie.toUiModel(): MovieUiModel = MovieUiModel(
    title,
    screeningStartDate,
    screeningEndDate,
    runningTime.value,
    posterResourceId,
    summary
)

fun MovieUiModel.toDomainModel(): Movie = Movie(
    title,
    screeningStartDate,
    screeningEndDate,
    Minute(runningTime),
    posterResourceId,
    summary
)

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
        repeat(Period.between(screeningStartDate, screeningEndDate).days + 1) {
            screeningDates.add(screeningDate)
            screeningDate = screeningDate.plusDays(1)
        }
        return screeningDates
    }
}
