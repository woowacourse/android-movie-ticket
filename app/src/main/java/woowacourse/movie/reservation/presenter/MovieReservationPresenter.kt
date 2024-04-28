package woowacourse.movie.reservation.presenter

import woowacourse.movie.common_data.MovieDataSource
import woowacourse.movie.reservation.contract.MovieReservationContract
import woowacourse.movie.reservation.model.DataResource
import woowacourse.movie.reservation.model.MovieReservationData
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

    override fun storeMovieId(movieId: Long) {
        MovieReservationData.movieId = movieId
    }

    override fun setMovieInfo() {
        val movieId = MovieReservationData.movieId.toInt()
        view.setMovieView(MovieDataSource.movieList[movieId])
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
        view.showSpinner(DataResource.screeningDates, DataResource.screeningTimesWeekdays)
    }

    override fun setSpinnerDateItemInfo() {
        view.setOnSpinnerDateItemSelectedListener(DataResource.screeningDates)
    }

    override fun setSpinnerTimeItemInfo() {
        view.setOnSpinnerTimeItemSelectedListener(DataResource.screeningTimesWeekdays)
    }
}
