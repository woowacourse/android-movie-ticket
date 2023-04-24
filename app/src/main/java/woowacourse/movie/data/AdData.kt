package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.presentation.main.MovieItem

object AdData {
    val ads = List(10000) { MovieItem.Ad(R.drawable.woowa_ad) }
}
