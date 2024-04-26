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

    override fun display(id: Long) {
        val movie = movieRepository.findOneById(id)
        movie?.let {
            detailContractView.onInitView(it)
            detailContractView.updateDate(it.dateTime.map { it.toLocalDate() }.toSet().toList())
            detailContractView.updateTime(
                it.dateTime.filter { localDateTime ->
                    it.dateTime.first().isSameDate(localDateTime)
                }.map {
                    it.toLocalTime()
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
        detailContractView.updateTime(
            movie.dateTime.filter { localDateTime ->
                localDateTime.toLocalDate().isEqual(localDate)
            }.map {
                it.toLocalTime()
            },
        )
    }

    override fun reservation(
        id: Long,
        localDate: LocalDate,
        localTime: LocalTime,
    ) {
        detailContractView.onReservationComplete(id, reservationCount.count, localDate, localTime)
    }
}
