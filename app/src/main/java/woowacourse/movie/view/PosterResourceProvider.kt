package woowacourse.movie.view

import woowacourse.movie.R

object PosterResourceProvider {

    fun getPosterResourceId(screeningId: Long): Int =
        when (screeningId) {
            1L -> R.drawable.harry_porter1_poster
            2L -> R.drawable.harry_porter2_poster
            3L -> R.drawable.harry_porter3_poster
            4L -> R.drawable.harry_porter4_poster
            5L -> R.drawable.harry_porter5_poster
            6L -> R.drawable.harry_porter6_poster
            7L -> R.drawable.harry_porter7_poster
            else -> R.drawable.harry_porter8_poster
        }
}
