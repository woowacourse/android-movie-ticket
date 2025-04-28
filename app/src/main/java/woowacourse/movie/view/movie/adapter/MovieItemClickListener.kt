package woowacourse.movie.view.movie.adapter

import woowacourse.movie.view.movie.model.AdUiModel
import woowacourse.movie.view.movie.model.MovieUiModel

interface MovieItemClickListener {
    fun onMovieClick(movie: MovieUiModel)

    fun onAdClick(ad: AdUiModel)
}
