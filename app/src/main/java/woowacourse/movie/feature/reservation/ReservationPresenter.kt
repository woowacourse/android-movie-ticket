package woowacourse.movie.feature.reservation

import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.domain.screening.BasicScreeningScheduleSystem
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.main.ui.toUiModel
import woowacourse.movie.feature.reservation.ui.toUiModel

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val screeningRepository: ScreeningRepository = MockScreeningRepository,
) :
    ReservationContract.Presenter {
    private lateinit var screening: Screening

    override fun fetchMovieDetails(movieId: Long) {
        screening = screeningRepository.find(movieId) ?: return
        val schedule =
            BasicScreeningScheduleSystem().getSchedules(screening.releaseDate, screening.endDate)
        view.initializeMovieDetails(screening.toUiModel())
        view.setupScreeningSchedulesControls(schedule.toUiModel())
    }
}
