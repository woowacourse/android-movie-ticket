package woowacourse.movie.feature.complete.ui

import android.content.Context
import woowacourse.movie.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun ReservationCompleteEntity.toReservationCompleteUiModel(context: Context): MovieReservationCompleteUiModel {
    return MovieReservationCompleteUiModel(
        movie.title,
        screeningDateMessage(movie.screeningDate),
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
    return context.resources.getString(R.string.reservation_count)
        .format(reservationCount)
}

private fun screeningDateMessage(screeningDate: LocalDate): String {
    return screeningDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
}
