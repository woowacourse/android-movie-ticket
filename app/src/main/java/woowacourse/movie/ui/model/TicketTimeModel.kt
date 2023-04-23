package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.TicketTime
import java.time.LocalDateTime

fun TicketTimeModel.mapToTicketTime() = TicketTime(dateTime)

fun TicketTime.mapToTicketTimeModel() = TicketTimeModel(dateTime)

@Parcelize
data class TicketTimeModel(val dateTime: LocalDateTime) : Parcelable
