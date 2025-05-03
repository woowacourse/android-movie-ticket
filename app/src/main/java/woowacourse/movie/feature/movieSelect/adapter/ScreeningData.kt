package woowacourse.movie.feature.movieSelect.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.movieSelect.screening.Movie
import woowacourse.movie.model.movieSelect.screening.Screening
import woowacourse.movie.view.model.ImageResource
import java.time.LocalDate

@Parcelize
data class ScreeningData(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val movieId: String,
    val runningTime: Int,
    val poster: ImageResource,
) : Parcelable {
    private fun toMovie(): Movie = Movie(title, runningTime, movieId)

    private fun toPeriod(): ClosedRange<LocalDate> = startDate..endDate

    fun toScreening(): Screening = Screening(toMovie(), toPeriod())
}
