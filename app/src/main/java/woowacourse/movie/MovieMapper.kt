package woowacourse.movie

object MovieMapper {

    val Movie.thumbnail: Int get() = getThumbnail(this.id)
    val Movie.poster: Int get() = getPoster(this.id)

    private val thumbnailMap: Map<Long, Int> = mapOf(
        1L to R.drawable.harry_potter_thumbnail,
        2L to R.drawable.iron_man_thumbnail,
        3L to R.drawable.suzume_thumbnail,
    )

    private val posterMap: Map<Long, Int> = mapOf(
        1L to R.drawable.harry_potter_poster,
        2L to R.drawable.iron_man_poster,
        3L to R.drawable.suzume_poster,
    )

    private fun getThumbnail(id: Long): Int {
        return thumbnailMap[id] ?: throw IllegalArgumentException("없는 아이디 입니다")
    }

    private fun getPoster(id: Long): Int {
        return posterMap[id] ?: throw IllegalArgumentException("없는 아이디 입니다")
    }
}
