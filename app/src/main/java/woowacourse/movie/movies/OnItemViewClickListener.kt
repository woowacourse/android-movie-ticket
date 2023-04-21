package woowacourse.movie.movies

import woowacourse.movie.model.Advertisement
import woowacourse.movie.model.MovieInfo

interface OnItemViewClickListener {

    fun onReservationButtonClicked(movieInfo: MovieInfo)
    fun onAdvertisementClicked(advertisement: Advertisement)
}
