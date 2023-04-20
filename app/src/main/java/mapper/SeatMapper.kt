package mapper

import data.Seat
import data.SeatClass
import data.SeatPosition
import model.SeatModel

fun Seat.toSeatModel() = SeatModel(
    row = this.position.row,
    column = this.position.col,
    seatClass = seatClass.toSeatClassModel(),
    isReserved = isReserved,
)

fun SeatModel.toSeat() = Seat(
    position = SeatPosition(row, column),
    seatClass = seatClass.toSeatClass(),
    isReserved = isReserved,
)

fun SeatClass.toSeatClassModel() = when (this) {
    SeatClass.SClass -> "SClass"
    SeatClass.AClass -> "AClass"
    SeatClass.BClass -> "BClass"
}

fun String.toSeatClass() = when (this) {
    "SClass" -> SeatClass.SClass
    "AClass" -> SeatClass.AClass
    "BClass" -> SeatClass.BClass
    else -> throw IllegalArgumentException()
}
