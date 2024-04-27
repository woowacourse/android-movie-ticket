package woowacourse.movie.presentation.detail

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.domain.ReservationCount
import woowacourse.movie.utils.MovieErrorCode
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieDetailPresenter(private val detailContractView: MovieDetailContract.View) :
    MovieDetailContract.Presenter {
    private var movieRepository: MovieRepository = MovieRepositoryImpl()
    private var reservationCount = ReservationCount()
    private var selectedLocalDate: LocalDate? = null

    override fun display(id: Long) {
        val movie = movieRepository.findMovieById(id)
        movie?.let {
            detailContractView.onInitView(it)
            detailContractView.updateDate(it.screenDateTime.map { it.dateTime.toLocalDate() }.toSet().toList())
            detailContractView.updateTime(
                it.screenDateTime.filter { screenDateTime ->
                    it.screenDateTime.first().dateTime.isSameDate(screenDateTime.dateTime)
                }.map {
                    it.dateTime.toLocalTime()
                },
            )
        } ?: detailContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
    }

    fun LocalDateTime.isSameDate(localDateTime: LocalDateTime): Boolean {
        return this.toLocalDate().isEqual(localDateTime.toLocalDate())
    }

    override fun plusReservationCount() {
        reservationCount.plus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun minusReservationCount() {
        reservationCount.minus()
        detailContractView.onCountUpdate(reservationCount.count)
    }

    override fun selectDate(
        movie: Movie,
        localDate: LocalDate,
    ) {
        if (selectedLocalDate == localDate) return

        selectedLocalDate = localDate
        detailContractView.updateTime(
            movie.screenDateTime.filter { screenDateTime ->
                screenDateTime.dateTime.toLocalDate().isEqual(localDate)
            }.map {
                it.dateTime.toLocalTime()
            },
        )
    }

    override fun selectSeat(
        movie: Movie,
        localDate: LocalDate,
        localTime: LocalTime,
    ) {
        val movieScreenTime = movie.screenDateTime.first { it.dateTime.isEqual(LocalDateTime.of(localDate, localTime)) }
        detailContractView.onSelectSeatClicked(movie.id, movieScreenTime.id, reservationCount.count)
    }
}
