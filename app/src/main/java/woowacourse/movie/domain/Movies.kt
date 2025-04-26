package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.R
import kotlin.IllegalArgumentException

@Parcelize
data class Movies(
    val movies: Map<Title, Movie>,
) : Parcelable {
    init {
        movies.keys.map { key ->
            require(key.value == movies[key]?.title) }
    }

    fun find(title: Title): Movie {
        return movies[title] ?: throw IllegalArgumentException(ERROR_MOVIE_NOT_FOUND)
    }

    fun toList(): List<Movie> = movies.values.toList()

    companion object {
        private const val ERROR_MOVIE_NOT_FOUND = "영화가 존재하지 않습니다."
        val value = listOf(
            Movie(
                Title("해리포터와 마법사의 돌"),
                R.drawable.movie_poster,
                ScreeningPeriod.ofDot("2025.04.01", "2025.04.30"),
                152
            )
        )
    }
}
