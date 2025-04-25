package woowacourse.movie.ui.view.movie

import woowacourse.movie.ui.model.movie.MovieUiModel

fun interface OnSelectMovieListener {
    fun onSelect(movieUiModel: MovieUiModel)
}
