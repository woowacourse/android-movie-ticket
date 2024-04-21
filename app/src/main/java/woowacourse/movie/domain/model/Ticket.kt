package woowacourse.movie.domain.model

data class Ticket(
    val title: String,
    val screeningDate: String,
    val count: Int,
    val price: Int = count * DEFAULT_MOVIE_PRICE,
) {
    companion object {
        const val DEFAULT_MOVIE_PRICE = 13_000
    }
}
