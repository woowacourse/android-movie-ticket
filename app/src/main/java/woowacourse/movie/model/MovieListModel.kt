package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

sealed class MovieListModel {

    data class MovieModel(
        @DrawableRes val poster: Int,
        val title: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val runningTime: Int,
        val description: String,
    ) : MovieListModel(), Serializable

    data class AdModel(
        @DrawableRes val banner: Int,
        val url: String
    ) : MovieListModel()
}
