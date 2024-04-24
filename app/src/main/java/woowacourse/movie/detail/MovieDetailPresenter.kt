package woowacourse.movie.detail

import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.ReservationCount
import woowacourse.movie.utils.MovieErrorCode

class MovieDetailPresenter(private val detailContractView: MovieDetailContract.View) :
    MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepository()
    private var reservationCount = ReservationCount()

    override fun display(id: Long) {
        val movie = movieRepository.getOneById(id)
        movie?.let {
            detailContractView.onInitView(it)
        } ?: detailContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
    }

    override fun plusReservationCount() {
        reservationCount.plus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun minusReservationCount() {
        reservationCount.minus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun reservation(id: Long) {
        detailContractView.onReservationComplete(id, reservationCount.count)
    }
}
