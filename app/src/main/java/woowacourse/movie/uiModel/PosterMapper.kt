package woowacourse.movie.uiModel
import woowacourse.movie.R

object PosterMapper {
    fun getPosterResourceId(key: String): Int =
        when (key) {
            "harry_potter_poster_1" -> R.drawable.harry_potter_poster_1
            "harry_potter_poster_2" -> R.drawable.harry_potter_poster_2
            "harry_potter_poster_3" -> R.drawable.harry_potter_poster_3
            "harry_potter_poster_4" -> R.drawable.harry_potter_poster_4
            else -> R.drawable.sample_poster
        }
}
