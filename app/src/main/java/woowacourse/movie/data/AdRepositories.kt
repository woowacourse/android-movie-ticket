package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.model.MovieAndAd

class AdRepositories {
    val ads = List(10) {
        MovieAndAd.Advertisement(
            R.drawable.woowa
        )
    }
}
