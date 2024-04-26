package woowacourse.movie.feature.completed

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.ReservationRepository
import woowacourse.movie.domain.TestFixture.MOCK_RESERVATION
import woowacourse.movie.feature.reservation.ui.toUiModel

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
}
