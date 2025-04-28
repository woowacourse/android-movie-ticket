package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.cinema.ticket.TicketBundle
import java.time.LocalDateTime

@Parcelize
class TicketBundleUiModel(
    val title: String,
    val size: Int,
    val dateTime: LocalDateTime,
    val totalPrice: Int,
    val labels: List<SeatUiModel>,
) : Parcelable

fun TicketBundle.toUiModel(): TicketBundleUiModel =
    TicketBundleUiModel(
        title,
        size,
        dateTime,
        totalPrice,
        labels.map { it.toUiModel() },
    )
