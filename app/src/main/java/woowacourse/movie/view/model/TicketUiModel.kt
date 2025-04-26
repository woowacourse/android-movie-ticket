package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.view.model.MovieListItem.MovieUiModel
import java.time.LocalDateTime

@Parcelize
data class TicketUiModel(
    val movie: MovieUiModel,
    val showtime: LocalDateTime,
    val count: Int,
) : Parcelable
