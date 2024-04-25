package woowacourse.movie.reservation.presenter

import woowacourse.movie.list.model.Movie
import woowacourse.movie.reservation.contract.MovieReservationContract
import woowacourse.movie.reservation.model.DataResource
import woowacourse.movie.reservation.model.MovieReservationMovieData
import woowacourse.movie.reservation.model.MovieReservationTicketCountData

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private val model: MovieReservationTicketCountData = MovieReservationTicketCountData()

    private val ticketCount
        get() = model.ticketCount

    override fun setCurrentResultTicketCountInfo() {
        view.showCurrentResultTicketCountView(ticketCount.number)
    }

    override fun storeMovieData(movieData: Movie) {
        MovieReservationMovieData.movieData = movieData
    }

    override fun setMovieInfo() {
        val movie = MovieReservationMovieData.movieData
        view.setMovieView(movie)
    }

    override fun setPlusButtonClickInfo() {
        model.plusTicketCount()
        view.showCurrentResultTicketCountView(ticketCount.number)
    }

    override fun setMinusButtonClickInfo() {
        runCatching {
            model.minusTicketCount()
            view.showCurrentResultTicketCountView(ticketCount.number)
        }.onFailure {
            view.showToast(it.message ?: "")
        }
    }

    override fun setTicketingButtonClickInfo() {
        view.startMovieTicketActivity(ticketCount)
    }

    override fun setSpinnerInfo() {
        view.showSpinnerInfo(DataResource.screeningDates, DataResource.screeningTimesWeekDays)
    }

    override fun setSpinnerDateItemInfo() {
        view.setOnSpinnerDateItemSelectedListener(DataResource.screeningDates)
    }

    override fun setSpinnerTimeItemInfo() {
        view.setOnSpinnerTimeItemSelectedListener(DataResource.screeningTimesWeekDays)
    }
}
