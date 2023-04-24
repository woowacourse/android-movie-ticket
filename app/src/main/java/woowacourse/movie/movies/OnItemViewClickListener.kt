package woowacourse.movie.movies

import woowacourse.movie.model.MoviesRecyclerItem

interface OnItemViewClickListener {

    fun onDisplayItemClicked(moviesRecyclerItem: MoviesRecyclerItem)
}
