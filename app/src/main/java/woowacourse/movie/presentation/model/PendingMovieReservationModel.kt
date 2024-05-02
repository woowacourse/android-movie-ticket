package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.MovieDateTime
import woowacourse.movie.domain.model.PendingMovieReservation
import java.io.Serializable

data class PendingMovieReservationModel(
    val movieId: Int,
    val title: String,
    val movieDate: MovieDateModel,
    val count: Int,
) : Serializable {
    companion object {
        private const val DEFAULT_PENDING_RESERVATION_TITLE = "예약정보가 존재하지 않습니다."
        val defaultPendingMovieReservation =
            PendingMovieReservationModel(
                movieId = -1,
                title = DEFAULT_PENDING_RESERVATION_TITLE,
                count = 0,
                movieDate = MovieDateTime().toMovieDateModel(),
            )
    }
}

fun PendingMovieReservation.toPendingMovieReservationModel(): PendingMovieReservationModel {
    return PendingMovieReservationModel(
        movieId = movieId,
        title = title,
        movieDate = movieDateTime.toMovieDateModel(),
        count = count,
    )
}
