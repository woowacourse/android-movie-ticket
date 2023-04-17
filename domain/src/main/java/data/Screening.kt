package data

class Screening(
    val movie: Movie,
    val reservation: Reservation,
) {
    val title: String get() = movie.title
}
