package woowacourse.movie.mapper

import com.example.domain.model.model.ReservationInfo
import woowacourse.movie.formatter.DateFormatter
import woowacourse.movie.formatter.TimeFormatter
import woowacourse.movie.model.PaymentModel
import woowacourse.movie.model.ReservationInfoModel

const val ticketDateFormat: String = "yyyy.M.d"
const val ticketTimeFormat: String = "HH:mm"

fun ReservationInfoModel.toTicket() = ReservationInfo(
    title,
    DateFormatter.formatToOriginal(playingDate, ticketDateFormat),
    TimeFormatter.formatToOriginal(playingTime, ticketTimeFormat),
    count,
    PaymentModel.of(payment).toPayment()
)

fun ReservationInfo.toTicketModel() =
    ReservationInfoModel(
        title,
        DateFormatter.formatToString(playingDate, ticketDateFormat),
        TimeFormatter.formatToString(playingTime, ticketTimeFormat),
        count,
        payment.toPaymentModel().string
    )
