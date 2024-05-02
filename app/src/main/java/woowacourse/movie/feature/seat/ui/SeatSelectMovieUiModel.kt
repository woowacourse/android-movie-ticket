package woowacourse.movie.feature.seat.ui

import woowacourse.movie.model.data.dto.Movie

data class SeatSelectMovieUiModel(val titleMessage: String) {
    companion object {
        fun from(movie: Movie): SeatSelectMovieUiModel {
            return SeatSelectMovieUiModel(movie.title)
        }
    }
}
