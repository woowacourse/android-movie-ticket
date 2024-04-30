package woowacourse.movie.model

class ScreeningMovies(val screeningMovies: List<ScreeningMovie>) {
    fun insertAdvertisements(advertiseInterval: Int): List<ScreenView> =
        screeningMovies.chunked(advertiseInterval) { chunk ->
            if (chunk.size == advertiseInterval) {
                chunk + Advertisement()
            } else {
                chunk
            }
        }.flatten()
}
