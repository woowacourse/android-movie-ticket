package woowacourse.movie

interface ReservationView {
    fun readMovieData(): Movie?

    fun setupReservationCompletedButton(movie: Movie)

    fun setupTicketQuantityControls()

    fun setQuantityText(newText: String)

    fun initializeMovieDetails(movie: Movie)

    fun moveToCompletedActivity(
        movie: Movie,
        quantity: Int,
    )
}
