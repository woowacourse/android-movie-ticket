package woowacourse.movie.feature.complete.ui

import android.content.Context
import woowacourse.movie.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ReservationCompleteEntity.toReservationCompleteUiModel(context: Context): MovieReservationCompleteUiModel {
    return MovieReservationCompleteUiModel(
        movie.title,
        screeningTimeMessage(ticket.screeningTime),
        reservationCountMessage(context, ticket.reservationCount.count),
        reservationAmountMessage(context, ticket.amount()),
    )
}

private fun reservationAmountMessage(
    context: Context,
    reservationAmount: Int,
): String {
    return context.resources.getString(R.string.reservation_amount).format(reservationAmount)
}

private fun reservationCountMessage(
    context: Context,
    reservationCount: Int,
): String {
    // TODO("예매한 좌석들")
    return context.resources.getString(R.string.reservation_count)
        .format(reservationCount, "")
}

private fun screeningTimeMessage(screeningTime: LocalDateTime): String {
    return screeningTime.format(DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"))
}
