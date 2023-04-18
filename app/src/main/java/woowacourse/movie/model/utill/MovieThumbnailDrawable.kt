package woowacourse.movie.model.utill

import woowacourse.movie.R

object MovieThumbnailDrawable {
    private val thumbnail = mapOf(
        1 to R.drawable.img_default_movie_thumbnail1,
        2 to R.drawable.img_default_movie_thumbnail2
    )

    fun getMovieThumbnail(id: Int): Int? = thumbnail[id]
}
