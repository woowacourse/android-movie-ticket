package mapper

import data.Seat
import data.SeatPosition
import data.SeatRank
import model.SeatModel
import model.SeatRankModel
import repository.SeatClassRepository

fun Seat.toSeatModel() = SeatModel(
    row = this.position.row,
    column = this.position.col,
    rank = seatRank.toSeatClassModel(),
    isReserved = isReserved,
)

fun SeatModel.toSeat() = Seat(
    position = SeatPosition(row, column),
    seatRank = rank.toSeatClass(),
    isReserved = isReserved,
)

fun SeatRank.toSeatClassModel() = when (this) {
    SeatRank.SRank -> SeatClassRepository.SRank
    SeatRank.ARank -> SeatClassRepository.ARank
    SeatRank.BRank -> SeatClassRepository.BRank
}.let { SeatClassRepository.seatRankMap[it] ?: throw IllegalArgumentException() }

fun SeatRankModel.toSeatClass() = when (this.name) {
    SeatClassRepository.SRank -> SeatRank.SRank
    SeatClassRepository.ARank -> SeatRank.ARank
    SeatClassRepository.BRank -> SeatRank.BRank
    else -> throw IllegalArgumentException()
}
