package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class Movie(
    @DrawableRes val imageSource: Int,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
) : Parcelable
