package model

import mapper.toSeatModel
import movie.SeatSelection
import java.io.Serializable
import java.time.LocalDateTime

data class TicketModel(
    val title: String,
    val reserveTime: LocalDateTime,
    val price: Int,
    val seats: List<SeatModel>,
) : Serializable {
    constructor(seatSelectionModel: SeatSelectionModel, seatSelection: SeatSelection) : this(
        seatSelectionModel.title,
        seatSelectionModel.reserveTime,
        seatSelection.getTotalPrice(seatSelectionModel.reserveTime),
        seatSelection.selection.map { it.toSeatModel() },
    )

    companion object {
        val EMPTY = TicketModel("", LocalDateTime.MIN, 0, emptyList())
    }
}
