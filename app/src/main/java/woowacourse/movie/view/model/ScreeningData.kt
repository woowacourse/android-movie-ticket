package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.screening.Screening
import java.time.LocalDate

@Parcelize
data class ScreeningData(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val movieId: String,
    val runningTime: Int,
) : Parcelable {
    fun toMovie(): Movie = Movie(title, runningTime, movieId)

    fun toPeriod(): ClosedRange<LocalDate> = startDate..endDate

    fun toScreening(): Screening = Screening(toMovie(), toPeriod())
}
