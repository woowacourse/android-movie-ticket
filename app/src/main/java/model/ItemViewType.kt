package model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

sealed interface ItemViewType {
    class Ad(
        @DrawableRes
        val image: Int,
    ) : ItemViewType {
        companion object {
            const val type = TYPE_AD
        }
    }

    class Movie(movie: MovieModel, val startDate: LocalDate, val endDate: LocalDate) : ItemViewType, Serializable {
        val title: String = movie.title
        val runTime: Int = movie.runTime
        val summary: String = movie.summary
        val poster: Int = movie.poster

        companion object {
            const val type = TYPE_MOVIE
        }
    }

    companion object {
        private const val TYPE_MOVIE = 1
        private const val TYPE_AD = 2
    }
}
