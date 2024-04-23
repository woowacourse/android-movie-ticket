package woowacourse.movie.screen.reservation

import woowacourse.movie.data.MockMovieRepository
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Quantity
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.screen.main.toUiModel

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    private val quantity = Quantity()
    private lateinit var movie: Movie

    override fun onStart() {
        val id = view.readMovieData() ?: return
        movie = MockMovieRepository.findMovieById(id) ?: return
        view.initializeMovieDetails(movie.toUiModel())
        view.setupReservationCompletedButton()
        view.setupTicketQuantityControls(quantity)
    }

    override fun onReservationCompleted() {
        val screening = Screening(movie, quantity = quantity)
        val reservation = Reservation(screening)
        view.moveToCompletedActivity(reservation)
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
