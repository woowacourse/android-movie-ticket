package woowacourse.movie.ui

import woowacourse.movie.R

object PosterIdMapper {
    fun posterIdByMovieId(id: Int): Int =
        when (id) {
            1 -> R.drawable.harry_potter_one
            2 -> R.drawable.harry_potter_two
            3 -> R.drawable.harry_potter_three
            4 -> R.drawable.harry_potter_four
            else -> R.drawable.harry_potter_one
        }
}
