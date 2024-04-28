package woowacourse.movie.domain.movie

import woowacourse.movie.domain.movie.ScreenView.Companion.currentId

class Screen private constructor(
    override val id: Long = currentId++,
    val movieId: Long,
): ScreenView {
    companion object {
        fun from(movie: Movie): Screen {
            return Screen(movieId = movie.id)
        }

        fun from(movieId: Long): Screen {
            return Screen(movieId = movieId)
        }

        fun fromMovieList(movieList: List<Movie>): List<Screen> {
            val screenList = mutableListOf<Screen>()
            movieList.forEach { movie ->
                screenList.add(from(movie))
            }
            return screenList.toList()
        }
    }
}
