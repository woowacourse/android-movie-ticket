package woowacourse.movie.feature.reservation

import woowacourse.movie.data.MockMovieRepository
import woowacourse.movie.data.MockReservationRepository
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.main.ui.toUiModel

class ReservationPresenter(private val view: ReservationContract.View) :
    ReservationContract.Presenter {
    private val quantity = Quantity()

    private lateinit var movie: Movie

    override fun fetchMovieDetails(id: Long) {
        movie = MockMovieRepository.find(id) ?: return
        view.initializeMovieDetails(movie.toUiModel())
        view.setupReservationCompleteControls()
        view.setupTicketQuantityControls(quantity)
    }

    override fun completeReservation() {
        val screening = Screening(movie)
        val id = MockReservationRepository.save(screening, quantity)
        view.navigateToCompleteScreen(id)
    }

    override fun increaseTicketQuantity() {
        quantity.increase()
        view.updateTicketQuantity("${quantity.value}")
    }

    override fun decreaseTicketQuantity() {
        quantity.decrease()
        view.updateTicketQuantity("${quantity.value}")
    }
}
