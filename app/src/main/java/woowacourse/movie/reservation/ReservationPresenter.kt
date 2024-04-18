package woowacourse.movie.reservation

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Quantity

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    private val quantity = Quantity()

    override fun onViewCreated() {
        val movie = view.readMovieData() ?: return
        view.initializeMovieDetails(movie)
        view.setupReservationCompletedButton(movie)
        view.setupTicketQuantityControls()
    }

    override fun onClicked(movie: Movie) {
        view.moveToCompletedActivity(movie, quantity.value)
    }

    override fun plus() {
        quantity.increase()
        view.setQuantityText("${quantity.value}")
    }

    override fun minus() {
        quantity.decrease()
        view.setQuantityText("${quantity.value}")
    }
}
