package woowacourse.movie.model

data class TicketModel(
    val reservationInfoModel: ReservationInfoModel,
    val price: Int,
    val seats: List<SeatModel>
) : java.io.Serializable
