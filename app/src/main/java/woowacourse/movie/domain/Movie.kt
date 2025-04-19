package woowacourse.movie.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    val title: String,
    @DrawableRes val poster: Int,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
) : Parcelable
