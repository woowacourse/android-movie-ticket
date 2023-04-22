package woowacourse.movie.model

const val ticketDateFormat: String = "yyyy.M.d"
const val ticketTimeFormat: String = "HH:mm"

data class TicketModel(
    val reservationInfoModel: ReservationInfoModel,
    val price: Int,
    val seats: List<SeatModel>
) : java.io.Serializable
