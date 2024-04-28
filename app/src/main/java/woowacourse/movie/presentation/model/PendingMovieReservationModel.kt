package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.PendingMovieReservation
import java.io.Serializable

data class PendingMovieReservationModel(
    val title: String,
    val movieDate: MovieDateModel,
    val count: Int,
) : Serializable


fun PendingMovieReservation.toPendingMovieReservationModel(): PendingMovieReservationModel {
    return PendingMovieReservationModel(
        title = title,
        movieDate = movieDate.toMovieDateModel(),
        count = count
    )
}
