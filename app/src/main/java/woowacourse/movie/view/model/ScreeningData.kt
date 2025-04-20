package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Screening
import java.time.LocalDate

@Parcelize
data class ScreeningData(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val posterId: Int,
    val runningTime: Int,
) : Parcelable {
    fun toMovie(): Movie = Movie(title, runningTime, posterId)

    fun toPeriod(): ClosedRange<LocalDate> = startDate..endDate

    fun toScreening(): Screening = Screening(toMovie(), toPeriod())
}
