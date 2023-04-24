package woowacourse.movie.ui.movielist.model

import woowacourse.movie.model.MovieUI

interface OnItemClick {
    fun onBookClick(item: MovieUI)
    fun onAdvertisementClick(item: AdvertisementUI)
}
