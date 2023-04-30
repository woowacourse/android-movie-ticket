package woowacourse.movie.ui.home.adapter

import woowacourse.movie.domain.MovieInfo

interface ItemClickListener {
    fun onMovieItemClick(movieUnit: MovieInfo.MovieUnit)
    fun onAdItemClick(ad: MovieInfo.Advertisement)
}
