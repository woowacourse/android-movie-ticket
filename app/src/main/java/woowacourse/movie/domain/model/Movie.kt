package woowacourse.movie.domain.model

data class Movie(
    val title: String,
    val runningTime: Int,
    val imageSrc: Int,
    val description: String,
)

data class Movie2(
    val id: Int,
    val title: String,
    val runningTime: Int,
    val description: String,
)
