package woowacourse.movie.domain.model.movies

class Movies(
    val items: List<Movie>,
) {
    operator fun get(idx: Int) = items[idx]

    fun size() = items.size
}
