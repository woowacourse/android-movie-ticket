package woowacourse.movie.model

object TicketData {
    val ticket =
        Ticket(
            MovieData.movieList[0].title,
            MovieData.movieList[0].screeningDate,
            13_000,
        )
}
