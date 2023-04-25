package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

sealed class MovieItem {
    @Parcelize
    data class MovieUI(
        val id: Int,
        val title: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val runningTime: Int,
        val introduce: String,
        @DrawableRes val thumbnail: Int?,
    ) : Parcelable, MovieItem()

    data class AdvertisementUI(
        @DrawableRes val image: Int?,
        val url: String
    ) : MovieItem()
}
