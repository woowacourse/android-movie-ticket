package woowacourse.movie.movies

import woowacourse.movie.model.DisplayItem

interface OnItemViewClickListener {

    fun onDisplayItemClicked(displayItem: DisplayItem)
}
