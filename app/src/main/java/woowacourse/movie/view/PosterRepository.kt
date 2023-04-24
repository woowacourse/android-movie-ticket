package woowacourse.movie.view

import woowacourse.movie.R

object PosterRepository {

    private const val NOT_EXIST_IMAGE_RESOURCE_ERROR = "해당 아이디를 가진 영화에 대한 포스터가 없습니다."

    private val movieImages: Map<Long, Int> = mapOf(1L to R.drawable.harry_porter_poster)

    fun getPosterResourceId(movieId: Long): Int {
        return movieImages[movieId] ?: throw IllegalArgumentException(NOT_EXIST_IMAGE_RESOURCE_ERROR)
    }
}
