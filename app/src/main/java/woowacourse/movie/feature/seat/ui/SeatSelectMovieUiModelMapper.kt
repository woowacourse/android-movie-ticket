package woowacourse.movie.feature.seat.ui

import woowacourse.movie.model.data.dto.Movie

fun Movie.toSeatSelectMovieUiModel(): SeatSelectMovieUiModel {
    return SeatSelectMovieUiModel(title)
}
