package woowacourse.movie.adapter

import woowacourse.movie.model.MovieListItem

interface ItemClickListener {
    fun onClick(item: MovieListItem)
}
