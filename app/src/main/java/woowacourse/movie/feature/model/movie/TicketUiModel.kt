package woowacourse.movie.feature.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.feature.model.movie.MovieListItem.MovieUiModel
import java.time.LocalDateTime

@Parcelize
data class TicketUiModel(
    val movie: MovieUiModel,
    val showtime: LocalDateTime,
    val count: Int,
) : Parcelable
