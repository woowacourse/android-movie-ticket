package woowacourse.movie.presenter

import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.model.Theater
import woowacourse.movie.repository.TheaterRepository

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val theaterRepository: TheaterRepository,
): SeatSelectionContract.Presenter {
    private lateinit var theater: Theater
    override fun loadTheater() {
        theater = theaterRepository.getTheater()
        view.displayTheater(theater)
    }
}
