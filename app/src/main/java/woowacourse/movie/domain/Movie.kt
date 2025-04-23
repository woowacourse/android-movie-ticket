package woowacourse.movie.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    @DrawableRes val poster: Int,
) : Parcelable {
    init {
        require(startDate <= endDate)
    }
}
