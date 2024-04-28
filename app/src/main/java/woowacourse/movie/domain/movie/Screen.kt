package woowacourse.movie.domain.movie

class Screen private constructor(
    val id: Long = currentId++,
    val movieId: Long,
) {
    companion object {
        var currentId = 1L

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
