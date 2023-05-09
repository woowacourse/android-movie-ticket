package woowacourse.movie.ui.home.adapter

import woowacourse.movie.domain.MovieInfo

interface ItemClickListener {
    fun onMovieItemClick(movie: MovieInfo.Movie)
    fun onAdItemClick(ad: MovieInfo.Advertisement)
}
