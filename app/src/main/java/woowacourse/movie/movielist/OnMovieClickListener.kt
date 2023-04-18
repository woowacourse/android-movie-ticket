package woowacourse.movie.movielist

import woowacourse.movie.dto.MovieDto

interface OnMovieClickListener {

    fun onMovieClick(movie: MovieDto)
}
