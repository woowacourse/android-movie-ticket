package woowacourse.movie.presentation.presenter

import woowacourse.movie.data.repository.MovieRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.contract.MovieDetailContract
import woowacourse.movie.presentation.uimodel.MovieUiModel
import java.time.format.DateTimeFormatter

class MovieDetailPresenterImpl(
    movieId: Int,
    private val movieRepository: MovieRepository = MovieRepositoryImpl,
) : MovieDetailContract.Presenter {
    private var view: MovieDetailContract.View? = null
    private val movie: Movie = movieRepository.findMovieById(movieId)
    private val movieTicket: MovieTicket = createMovieTicket()

    private fun createMovieTicket(): MovieTicket {
        return MovieTicket(
            movie.title,
            movie.screeningInfo.startDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
        )
    }

    override fun attachView(view: MovieDetailContract.View) {
        this.view = view
        onViewSetUp()
    }

    override fun detachView() {
        this.view = null
    }

    override fun onViewSetUp() {
        view?.showMovieDetail(MovieUiModel(movie))
    }

    override fun minusReservationCount() {
        movieTicket.minusCount()
        view?.showReservationCount(movieTicket.count)
    }

    override fun plusReservationCount() {
        movieTicket.plusCount()
        view?.showReservationCount(movieTicket.count)
    }

    override fun initReservationCount(count: Int) {
        movieTicket.initCount(count)
        view?.showReservationCount(movieTicket.count)
    }

    override fun onReserveButtonClicked() {
        view?.moveToReservationResult(
            title = movieTicket.movieTitle,
            screeningStartDate = movieTicket.screeningDate,
            reservationCount = movieTicket.count,
            totalPrice = movieTicket.totalPrice(),
        )
    }
}
