package model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

sealed interface ItemViewType {
    val type: Int

    class AD(
        @DrawableRes
        val image: Int,
    ) : ItemViewType {
        override val type: Int = TYPE_AD
    }

    class MOVIE(movie: MovieModel, val startDate: LocalDate, val endDate: LocalDate) : ItemViewType, Serializable {
        val title: String = movie.title
        val runTime: Int = movie.runTime
        val summary: String = movie.summary
        val poster: Int = movie.poster
        override val type: Int = TYPE_MOVIE
    }

    companion object {
        const val TYPE_MOVIE = 1
        const val TYPE_AD = 2
    }
}
