package movie

import data.SeatClass

object CinemaConfigure {
    fun getPrice(seatClass: SeatClass): Int = when (seatClass) {
        SeatClass.SClass -> 15_000
        SeatClass.AClass -> 12_000
        SeatClass.BClass -> 10_000
    }

    val seats = listOf(
        listOf(SeatClass.BClass, SeatClass.BClass, SeatClass.BClass, SeatClass.BClass),
        listOf(SeatClass.SClass, SeatClass.SClass, SeatClass.SClass, SeatClass.SClass),
        listOf(SeatClass.SClass, SeatClass.SClass, SeatClass.SClass, SeatClass.SClass),
        listOf(SeatClass.AClass, SeatClass.AClass, SeatClass.AClass, SeatClass.AClass),
        listOf(SeatClass.AClass, SeatClass.AClass, SeatClass.AClass, SeatClass.AClass),
    )
}
