package woowacourse.movie

class ReservationPresenter(private val view: ReservationView) {
    private var quantity = MINIMUM_QUANTITY

    fun onViewCreated() {
        val movie = view.readMovieData() ?: return
        view.initializeMovieDetails(movie)
        view.setupReservationCompletedButton(movie)
        view.setupTicketQuantityControls()
    }

    fun onClicked(movie: Movie) {
        view.moveToCompletedActivity(movie, quantity)
    }

    fun plus() {
        quantity++
        view.setQuantityText("$quantity")
    }

    fun minus() {
        quantity = (quantity - 1).coerceAtLeast(MINIMUM_QUANTITY)
        view.setQuantityText("$quantity")
    }

    companion object {
        const val MINIMUM_QUANTITY = 1
    }
}
