package woowacourse.movie.domain.policy

import woowacourse.movie.domain.model.advertisement.Advertisement
import woowacourse.movie.domain.model.movie.Movie

class MovieAdvertisementPolicy {
    fun movieAdvertisement(
        movies: List<Movie>,
        advertisements: List<Advertisement>,
    ): List<MovieContent> {
        val result = mutableListOf<MovieContent>()
        var adIndex = 0

        movies.chunked(3).forEach { movieChunk ->
            result += movieChunk.map { it }
            if (movieChunk.size == 3 && advertisements.isNotEmpty()) {
                result += advertisements[adIndex % advertisements.size]
                adIndex++
            }
        }

        return result
    }
}
