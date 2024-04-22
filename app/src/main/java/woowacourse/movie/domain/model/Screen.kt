package woowacourse.movie.domain.model

data class Screen(
    val id: Int,
    val movie: Movie,
    val date: String,
    val price: Int,
)
