package woowacourse.movie.model

object TicketDataResource {
    val ticket =
        Ticket(
            MovieDataSource.movieList[0].title,
            MovieDataSource.movieList[0].screeningDate,
            13_000,
        )
}
