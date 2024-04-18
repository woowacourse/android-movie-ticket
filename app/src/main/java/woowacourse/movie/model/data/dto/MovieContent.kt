package woowacourse.movie.model.data.dto

data class MovieContent(
    val imageId: Int,
    val title: String,
    val screeningDate: Date,
    val runningTime: Int,
    val synopsis: String,
    val id: Long = 0,
)

val nullMovieContent =
    MovieContent(
        0,
        "오류가 발생했습니다.",
        Date(0, 0, 0),
        0,
        "",
    )
