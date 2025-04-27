package woowacourse.movie.view.model.movie

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

sealed class MovieListItem {
    @Parcelize
    data class MovieUiModel(
        val title: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val runningTime: Int,
        @DrawableRes val poster: Int,
    ) : MovieListItem(), Parcelable

    data class Ad(
        @DrawableRes val image: Int,
    ) : MovieListItem()
}
