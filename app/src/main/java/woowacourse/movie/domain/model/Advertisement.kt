package woowacourse.movie.domain.model

import woowacourse.movie.R

class Advertisement(
    val image: Int,
) {
    companion object {
        val DUMMY_ADS =
            listOf(
                Advertisement(
                    R.drawable.woowacourse,
                ),
            )
    }
}
