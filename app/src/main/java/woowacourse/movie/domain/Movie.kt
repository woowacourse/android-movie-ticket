package woowacourse.movie.domain

import woowacourse.movie.R
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val image: Int,
    val title: String,
    val date: Date,
    val time: Int,
) : Serializable {
    constructor() : this(
        R.drawable.ic_launcher_background,
        UNTITLED_MOVIE_TITLE,
        Date(
            DEFAULT_DATE_TIME,
            DEFAULT_DATE_TIME,
        ),
        DEFAULT_RUNNING_TIME,
    )

    companion object {
        private val DEFAULT_DATE_TIME = LocalDate.of(2025, 1, 1)
        private const val UNTITLED_MOVIE_TITLE = "untitled"
        private const val DEFAULT_RUNNING_TIME = 0
    }
}
