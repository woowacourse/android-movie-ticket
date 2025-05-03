package woowacourse.movie.domain.policy

import woowacourse.movie.domain.model.advertisement.Advertisement
import woowacourse.movie.domain.model.movie.Movie

class MovieAdvertisementPolicy {
    fun movieAdvertisement(
        movies: List<Movie>,
        advertisements: List<Advertisement>,
        interval: Int,
    ): List<MovieContent> {
        return movies.chunked(interval)
            .flatMapIndexed { index, movieChunk ->
                val selectedAdvertisement =
                    selectAdvertisement(index, movieChunk.size, interval, advertisements)
                movieChunk + selectedAdvertisement
            }
    }
}

private fun selectAdvertisement(
    chunkIndex: Int,
    chunkSize: Int,
    interval: Int,
    advertisements: List<Advertisement>,
): List<MovieContent> {
    if (chunkSize < interval || advertisements.isEmpty()) return emptyList()
    val advertisement = advertisements[chunkIndex % advertisements.size]
    return listOf(advertisement)
}
