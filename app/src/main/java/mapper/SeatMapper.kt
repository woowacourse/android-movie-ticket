package mapper

import data.Seat
import data.SeatClass
import model.SeatModel

fun Seat.toSeatModel() = SeatModel(
    row = this.position.row,
    column = this.position.col,
    seatClass = seatClass.toSeatClassModel(),
    isReserved = isReserved,
)

fun SeatClass.toSeatClassModel() = when (this) {
    SeatClass.SClass -> "SClass"
    SeatClass.AClass -> "AClass"
    SeatClass.BClass -> "BClass"
}
