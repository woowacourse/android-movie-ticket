package woowacourse.movie.main

import woowacourse.movie.domain.Movie

sealed interface Item {
    data class MovieItem(val movie: Movie) : Item

    data class AdvertisementItem(val imageId: Int) : Item
}
