package woowacourse.movie.feature.seat.ui

import woowacourse.movie.model.data.dto.Movie

class SeatSelectMovieUiModel(val titleMessage: String) {
    companion object {
        fun from(movie: Movie): SeatSelectMovieUiModel {
            return SeatSelectMovieUiModel(movie.title)
        }
    }
}
