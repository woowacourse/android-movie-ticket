package woowacourse.movie.model.data.dto

data class MovieContent(
    val imageId: Int,
    val title: String,
    val screeningDate: Date,
    val runningTime: Int,
    val synopsis: String,
    val id: Long = 0,
)
