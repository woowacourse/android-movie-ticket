package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

sealed class MovieListModel {

    @Parcelize
    data class MovieModel(
        @DrawableRes val poster: Int,
        val title: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val runningTime: Int,
        val description: String,
    ) : MovieListModel(), Parcelable

    data class AdModel(
        @DrawableRes val banner: Int,
        val url: String
    ) : MovieListModel()
}
