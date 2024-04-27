package woowacourse.movie.domain

data class Movie(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val screenDateTime: List<ScreenDateTime>,
    val runningTime: Int,
)
