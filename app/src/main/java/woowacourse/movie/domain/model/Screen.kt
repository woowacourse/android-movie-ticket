package woowacourse.movie.domain.model

data class Screen(
    val id: Int,
    val movie: Movie,
    val date: String,
    val price: Int,
)

data class Screen2(
    val id: Int,
    val movie: Movie2,
    val date: String,
    val price: Int,
)
