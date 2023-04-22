package woowacourse.movie.ui.movielist

import woowacourse.movie.model.AdvertisementUI
import woowacourse.movie.model.MovieUI

interface OnItemClick {
    fun onBookClick(item: MovieUI)
    fun onAdvertisementClick(item: AdvertisementUI)
}
