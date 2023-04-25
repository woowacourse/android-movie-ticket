package woowacourse.movie.ui.movielist.data

import com.woowacourse.movie.domain.Movie
import woowacourse.movie.model.MovieItem
import woowacourse.movie.model.mapper.toMovieUI

object MovieRepository {
    fun allMovies(): List<MovieItem.MovieUI> = movies
    private val movies: List<MovieItem.MovieUI> = Movie.provideDummy().map { it.toMovieUI() }
}
