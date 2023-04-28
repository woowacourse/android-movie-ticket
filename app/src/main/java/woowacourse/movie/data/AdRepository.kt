package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.model.MovieAndAd

class AdRepository {
    val ads = List(AD_NUMBER) {
        MovieAndAd.Advertisement(
            R.drawable.woowa,
            "https://woowacourse.github.io/"
        )
    }

    companion object {
        private const val AD_NUMBER = 10
    }
}
