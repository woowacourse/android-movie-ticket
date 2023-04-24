package woowacourse.movie.movies

import woowacourse.movie.model.MovieRecyclerItem

fun interface OnItemViewClickListener {

    fun onDisplayItemClicked(movieRecyclerItem: MovieRecyclerItem)
}
