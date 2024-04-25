package woowacourse.movie.presentation.detail

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.domain.ReservationCount
import woowacourse.movie.utils.MovieErrorCode

class MovieDetailPresenter(private val detailContractView: MovieDetailContract.View) :
    MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepositoryImpl()
    private var reservationCount = ReservationCount()

    override fun display(id: Long) {
        val movie = movieRepository.findOneById(id)
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
