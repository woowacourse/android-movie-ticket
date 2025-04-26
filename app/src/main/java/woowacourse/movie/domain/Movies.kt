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
            require(key.value == movies[key]?.title)
        }
    }

    fun find(title: Title): Movie {
        return movies[title] ?: throw IllegalArgumentException(ERROR_MOVIE_NOT_FOUND)
    }

    fun toList(): List<Movie> = movies.values.toList()

    companion object {
        private const val ERROR_MOVIE_NOT_FOUND = "영화가 존재하지 않습니다."
        val value = Movies(
            mapOf(
                Title("해리포터와 마법사의 돌") to
                        Movie(
                            Title("해리포터와 마법사의 돌"),
                            R.drawable.movie_poster,
                            ScreeningPeriod.ofDot("2025.04.01", "2025.04.25"),
                            152
                        ),
                Title("해리포터와 비밀의 방") to
                        Movie(
                            Title("해리포터와 비밀의 방"),
                            R.drawable.harry_potter_and_the_chamber_of_secrets,
                            ScreeningPeriod.ofDot("2025.04.01", "2025.04.28"),
                            162
                        ),
                Title("해리포터와 아즈카반의 죄수") to
                        Movie(
                            Title("해리포터와 아즈카반의 죄수"),
                            R.drawable.harry_potter_and_the_prisoner_of_azkaban,
                            ScreeningPeriod.ofDot("2025.05.01", "2025.05.31"),
                            141
                        ),
                Title("해리포터와 불의 잔") to
                        Movie(
                            Title("해리포터와 불의 잔"),
                            R.drawable.harry_potter_and_the_goblet_of_fire,
                            ScreeningPeriod.ofDot("2025.06.01", "2025.06.30"),
                            157
                        ),
            )
        )
    }
}
