package woowacourse.movie.data

import woowacourse.movie.R

object MovieDrawableData {

    private val moviePosters = mapOf(
        1L to R.drawable.harry_potter_poster,
        2L to R.drawable.iron_man_poster,
        3L to R.drawable.suzume_poster,
    )

    private val movieThumbnails = mapOf(
        1L to R.drawable.harry_potter_thumbnail,
        2L to R.drawable.iron_man_thumbnail,
        3L to R.drawable.suzume_thumbnail,
    )

    fun getMoviePoster(movieId: Long): Int? {
        return moviePosters[movieId]
    }

    fun getMovieThumbnail(movieId: Long): Int? {
        return movieThumbnails[movieId]
    }
}
