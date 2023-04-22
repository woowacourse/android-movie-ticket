package woowacourse.movie.listener

import woowacourse.movie.model.MovieListItem

interface ItemClickListener {
    fun onClick(item: MovieListItem)
}
