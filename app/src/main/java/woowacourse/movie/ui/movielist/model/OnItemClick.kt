package woowacourse.movie.ui.movielist.model

import woowacourse.movie.model.MovieItem

interface OnItemClick {
    fun onBookClick(item: MovieItem.MovieUI)
    fun onAdvertisementClick(item: MovieItem.AdvertisementUI)
}
