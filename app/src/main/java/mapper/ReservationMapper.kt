package mapper

import data.Reservation
import model.ReservationModel

fun ReservationModel.toReservation() = Reservation(
    startDate = startDate,
    endDate = endDate,
)

fun Reservation.toReservationModel() = ReservationModel(
    startDate = startDate,
    endDate = endDate,
)
