package woowacourse.movie.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

sealed class MovieInfo {
    data class Advertisement(
        @DrawableRes val adImage: Int,
        val url: String,
    ) : MovieInfo()

    @Parcelize
    data class Movie(
        val id: Long,
        val title: String,
        val screeningStartDate: LocalDate,
        val screeningEndDate: LocalDate,
        val runningTime: Int,
        val description: String,
        val thumbnail: Int,
        val poster: Int,
    ) : Parcelable, MovieInfo()
}
