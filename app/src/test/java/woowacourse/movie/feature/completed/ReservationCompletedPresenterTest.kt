package woowacourse.movie.feature.completed

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.TicketRepository
import woowacourse.movie.domain.TestFixture.MOCK_TICKET
import woowacourse.movie.feature.reservation.ui.toUiModel

class ReservationCompletedPresenterTest {
    private lateinit var presenter: ReservationCompletedPresenter
    private lateinit var view: ReservationCompletedContract.View
    private lateinit var repository: TicketRepository

    @BeforeEach
    fun setup() {
        view = mockk<ReservationCompletedContract.View>()
        repository = mockk<TicketRepository>()
        presenter = ReservationCompletedPresenter(view, repository)
    }

    @Test
    fun `예매 정보를 가져와 화면에 표시한다`() {
        // given
        every { repository.find(any()) } returns MOCK_TICKET
        every { view.initializeReservationDetails(any()) } just runs

        // when
        presenter.fetchReservationDetails(0)

        // then
        verify { view.initializeReservationDetails(MOCK_TICKET.toUiModel()) }
    }
}
