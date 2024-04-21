package woowacourse.movie.model

object TicketData {
    val ticket =
        Ticket(
            MovieDataSource.movieList[0].title,
            MovieDataSource.movieList[0].screeningDate,
            13_000,
        )
}
