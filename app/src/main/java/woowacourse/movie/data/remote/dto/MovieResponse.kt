package woowacourse.movie.data.remote.dto

data class MovieResponse(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val description: String,
    val dateTime: List<ScreenDateTimeResponse>,
    val runningTime: Int,
)
