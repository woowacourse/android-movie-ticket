package woowacourse.movie.movieList

import woowacourse.movie.uiModel.MovieInfoUIModel

interface MovieRepository {
    fun getMovies(): List<MovieInfoUIModel>
}
