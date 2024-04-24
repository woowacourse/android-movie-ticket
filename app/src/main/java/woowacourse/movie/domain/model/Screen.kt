package woowacourse.movie.domain.model

data class Screen(
    val id: Int,
    val movie: Movie,
    val date: String,
    val price: Int,
) {
    companion object {
        val NULL =
            Screen(
                id = -1,
                movie = Movie.NULL,
                date = "",
                price = -1,
            )
    }
}
