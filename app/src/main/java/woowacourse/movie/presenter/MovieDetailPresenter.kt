package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieDetailContract
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.schedule.ScreeningPeriod
import woowacourse.movie.model.schedule.WeekdayTimeTable
import woowacourse.movie.model.schedule.WeekendTimeTable
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.repository.PseudoMovieRepository

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    private val movieRepository: MovieRepository = PseudoMovieRepository(),
    private var ticketNum: Int = 1,
) : MovieDetailContract.Presenter {
    private lateinit var period: ScreeningPeriod
    private var movieId: Int = -1
    private lateinit var movie: Movie
    private lateinit var screeningDateTime: ScreeningDateTime

    override fun loadMovie(movieId: Int) {
        this.movieId = movieId
        movie = movieRepository.getMovie(movieId)
        view.displayMovie(movie)
    }

    override fun loadScreeningPeriod(period: ScreeningPeriod) {
        this.period = period
        view.displayScreeningDates(period.dates)
    }

    override fun plusTicketNum() {
        ticketNum += 1
        view.displayTicketNum(ticketNum)
    }

    override fun minusTicketNum() {
        if (ticketNum > 1) {
            ticketNum -= 1
            view.displayTicketNum(ticketNum)
        }
    }

    override fun confirm() {
        view.navigateToSeatSelection(movieId, ticketNum, screeningDateTime.dateTime)
    }

    override fun selectScreeningDate(date: ScreeningDate) {
        val timeTable = if (date.isWeekend()) WeekendTimeTable(date) else WeekdayTimeTable(date)
        val times = timeTable.getScreeningTimes()
        view.displayScreeningTimes(times)
    }

    override fun selectScreeningTime(time: ScreeningDateTime) {
        screeningDateTime = time
    }
}
