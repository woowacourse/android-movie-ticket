package woowacourse.movie.presentation.detail

import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.utils.MovieErrorCode
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieDetailPresenter(
    private val detailContractView: MovieDetailContract.View,
    private val movieRepository: MovieRepository = MovieRepositoryImpl(),
) : MovieDetailContract.Presenter {
    private var uiModel: DetailUiModel = DetailUiModel()

    override fun loadMovie(id: Long) {
        val movie = movieRepository.findMovieById(id)
        movie?.let {
            val dates = it.screenDateTime.map { it.dateTime.toLocalDate() }.toSet().toList()
            val times =
                it.screenDateTime
                    .filter { screenDateTime -> it.screenDateTime.first().dateTime.isSameDate(screenDateTime.dateTime) }
                    .map {
                        it.dateTime.toLocalTime()
                    }
            uiModel =
                uiModel.copy(
                    movie = it,
                    localDates = dates,
                    localTimes = times,
                    selectedLocalDate = dates.firstOrNull(),
                    selectedLocalTime = times.firstOrNull(),
                )
            detailContractView.onUpdateView(uiModel)
            detailContractView.onUpdateDate(dates, 0)
            detailContractView.onUpdateTime(times, 0)
        } ?: detailContractView.onError(MovieErrorCode.INVALID_MOVIE_ID)
    }

    override fun plusReservationCount() {
        uiModel =
            uiModel.copy(
                reservationCount = uiModel.reservationCount + 1,
            )
        detailContractView.onUpdateView(uiModel)
    }

    override fun minusReservationCount() {
        uiModel =
            uiModel.copy(
                reservationCount = uiModel.reservationCount - 1,
            )
        detailContractView.onUpdateView(uiModel)
    }

    override fun selectDate(
        localDate: LocalDate,
        position: Int,
    ) {
        if (localDate == uiModel.selectedLocalDate) return // Spinner의 최초 itemSelectedListener 호출 (사람이 선택하지 않은 경우) 무시
        uiModel =
            uiModel.copy(
                localTimes =
                    uiModel.movie?.screenDateTime?.filter { screenDateTime ->
                        screenDateTime.dateTime.toLocalDate().isEqual(localDate)
                    }?.map {
                        it.dateTime.toLocalTime()
                    },
                selectedLocalDate = localDate,
                selectedLocalTime = null,
            )
        detailContractView.onUpdateTime(uiModel.localTimes, null)
    }

    override fun selectSeat(
        localDate: LocalDate,
        localTime: LocalTime,
    ) {
        val movieScreenTime = uiModel.movie?.screenDateTime?.firstOrNull { it.dateTime.isEqual(LocalDateTime.of(localDate, localTime)) }
        detailContractView.onSelectSeatClicked(uiModel.movie?.id, movieScreenTime?.id, uiModel.reservationCount.count)
    }

    override fun restoreDateTime(
        localDate: LocalDate,
        localTime: LocalTime,
    ) {
        uiModel =
            uiModel.copy(
                selectedLocalDate = localDate,
                selectedLocalTime = localTime,
            )
        detailContractView.onUpdateDate(uiModel.localDates, uiModel.localDates?.indexOfFirst { it == localDate })
        detailContractView.onUpdateTime(uiModel.localTimes, uiModel.localTimes?.indexOfFirst { it == localTime })
    }

    private fun LocalDateTime.isSameDate(localDateTime: LocalDateTime): Boolean {
        return this.toLocalDate().isEqual(localDateTime.toLocalDate())
    }
}
