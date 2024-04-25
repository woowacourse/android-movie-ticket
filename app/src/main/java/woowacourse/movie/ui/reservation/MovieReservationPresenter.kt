package woowacourse.movie.ui.reservation

import woowacourse.movie.model.data.MovieContents
import woowacourse.movie.model.movie.ReservationCount
import woowacourse.movie.model.movie.ScreeningDateTime
import java.time.LocalDate

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieContents: MovieContents,
) :
    MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount
    private lateinit var screeningDateTime: ScreeningDateTime
    private lateinit var movieTime: String

    override fun updateReservationCount(count: Int) {
        reservationCount = ReservationCount(count)
        view.updateReservationCount(count)
    }

    override fun selectDate(date: LocalDate) {
        screeningDateTime = ScreeningDateTime(date)
        view.showMovieTimeSelection(screeningDateTime.screeningTime())
    }

    override fun selectTime(time: String) {
        movieTime = time
    }

    override fun loadMovieContent(movieContentId: Long) {
        try {
            val movieContent = movieContents.find(movieContentId)
            view.showMovieContent(movieContent)
            view.showMovieDateSelection(movieContent.getDatesInRange())
        } catch (e: NoSuchElementException) {
            view.showError(e)
        }
    }

    override fun decreaseCount() {
        reservationCount--
        view.updateReservationCount(reservationCount.count)
    }

    override fun increaseCount() {
        reservationCount++
        view.updateReservationCount(reservationCount.count)
    }

    override fun reserveMovie() {
        view.moveMovieReservationCompleteView(
            reservationCount.count,
            screeningDateTime.date.toString(),
            movieTime,
        )
    }

    override fun handleError(throwable: Throwable) {
        view.showError(throwable)
    }
}
