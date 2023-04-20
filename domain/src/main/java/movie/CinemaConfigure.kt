package movie

import data.SeatClass
import data.SeatPosition

object CinemaConfigure {
    fun getPriceOf(seatClass: SeatClass): Int = when (seatClass) {
        SeatClass.SClass -> 15_000
        SeatClass.AClass -> 12_000
        SeatClass.BClass -> 10_000
    }

    val seats = mapOf(
        SeatPosition(0, 0) to SeatClass.BClass,
        SeatPosition(0, 1) to SeatClass.BClass,
        SeatPosition(0, 2) to SeatClass.BClass,
        SeatPosition(0, 3) to SeatClass.BClass,
        SeatPosition(1, 0) to SeatClass.BClass,
        SeatPosition(1, 1) to SeatClass.BClass,
        SeatPosition(1, 2) to SeatClass.BClass,
        SeatPosition(1, 3) to SeatClass.BClass,
        SeatPosition(2, 0) to SeatClass.SClass,
        SeatPosition(2, 1) to SeatClass.SClass,
        SeatPosition(2, 2) to SeatClass.SClass,
        SeatPosition(2, 3) to SeatClass.SClass,
        SeatPosition(3, 0) to SeatClass.SClass,
        SeatPosition(3, 1) to SeatClass.SClass,
        SeatPosition(3, 2) to SeatClass.SClass,
        SeatPosition(3, 3) to SeatClass.SClass,
        SeatPosition(4, 0) to SeatClass.AClass,
        SeatPosition(4, 1) to SeatClass.AClass,
        SeatPosition(4, 2) to SeatClass.AClass,
        SeatPosition(4, 3) to SeatClass.AClass,
    )
}
