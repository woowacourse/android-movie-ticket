package woowacourse.movie.feature.completed

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import woowacourse.movie.data.ReservationRepository
import woowacourse.movie.domain.pricing.UniformPricingSystem
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.reservation.Reservation
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Schedule
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.reservation.ui.toUiModel
import java.time.LocalDate

class ReservationCompletedPresenterTest {
    private lateinit var presenter: ReservationCompletedPresenter
    private lateinit var view: ReservationCompletedContract.View
    private lateinit var repository: ReservationRepository

    @BeforeEach
    fun setup() {
        view = mockk<ReservationCompletedContract.View>()
        repository = mockk<ReservationRepository>()
        presenter = ReservationCompletedPresenter(view, repository)
    }

    @Test
    fun `예매 정보를 가져와 화면에 표시한다`() {
        // given
        every { repository.find(any()) } returns MOCK_RESERVATION
        every { view.initializeReservationDetails(any()) } just runs

        // when
        presenter.fetchReservationDetails(0)

        // then
        verify { view.initializeReservationDetails(MOCK_RESERVATION.toUiModel()) }
    }

    companion object {
        val MOCK_RESERVATION =
            Reservation(
                0,
                Screening(
                    Movie(0, R.drawable.poster, "제목", "설명", "2024.3.1", 120),
                    Schedule(LocalDate.of(2024, 3, 1)),
                ),
                Quantity(3),
                UniformPricingSystem(),
            )
    }
}
