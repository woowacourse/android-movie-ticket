package woowacourse.movie.reservation

import woowacourse.movie.model.Movie

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    private var quantity = MINIMUM_QUANTITY

    override fun onViewCreated() {
        val movie = view.readMovieData() ?: return
        view.initializeMovieDetails(movie)
        view.setupReservationCompletedButton(movie)
        view.setupTicketQuantityControls()
    }

    override fun onClicked(movie: Movie) {
        view.moveToCompletedActivity(movie, quantity)
    }

    override fun plus() {
        quantity++
        view.setQuantityText("$quantity")
    }

    override fun minus() {
        quantity = (quantity - 1).coerceAtLeast(MINIMUM_QUANTITY)
        view.setQuantityText("$quantity")
    }

    companion object {
        const val MINIMUM_QUANTITY = 1
    }
}
