package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
sealed class MovieAndAd : Parcelable {
    class Movie constructor(
        @DrawableRes
        val imgResourceId: Int,
        val title: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val runningTime: RunningTime,
        val description: String,
    ) : MovieAndAd()

    class Advertisement constructor(
        @DrawableRes
        val imgResourceId: Int,
        val url: String,
    ) : MovieAndAd()
}
