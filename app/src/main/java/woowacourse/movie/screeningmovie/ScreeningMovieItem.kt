package woowacourse.movie.screeningmovie

sealed interface ScreeningMovieItem

fun insertAdvertisements(movies: List<ScreenMovieUiModel>, advertiseInterval:Int): List<ScreeningMovieItem> =
    movies.chunked(advertiseInterval) { chunk ->
        if (chunk.size == advertiseInterval) {
            chunk + AdvertiseUiModel()
        } else {
            chunk
        }
    }.flatten()

