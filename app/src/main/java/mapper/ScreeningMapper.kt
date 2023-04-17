package mapper

import data.Screening
import model.ScreeningModel

fun ScreeningModel.toScreening() = Screening(
    movie = movieModel.toMovie(),
    reservation = reservationModel.toReservation(),
)

fun Screening.toScreeningModel(poster: Int) = ScreeningModel(
    movieModel = movie.toMovieModel(poster),
    reservationModel = reservation.toReservationModel(),
)
