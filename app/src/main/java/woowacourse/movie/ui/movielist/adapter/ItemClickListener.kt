package woowacourse.movie.ui.movielist.adapter

import woowacourse.movie.model.MovieListModel

interface ItemClickListener {
    fun onMovieItemClick(movie: MovieListModel.MovieModel)
    fun onAdItemClick(ad: MovieListModel.AdModel)
}
