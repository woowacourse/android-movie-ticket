package woowacourse.movie

data class Movie(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: ImageUrl = ImageUrl.none(),
    val runningTime: RunningTime,
)