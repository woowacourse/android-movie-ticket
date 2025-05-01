package woowacourse.movie.presenter.reservationComplete

import io.kotest.core.spec.style.AnnotationSpec.After
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.presenter.MOVIE_TICKET_B1_C3

class ReservationCompletePresenterTest {
    private lateinit var presenter: ReservationCompletePresenter
    private lateinit var view: ReservationCompleteContracts.View

    @BeforeEach
    fun setup() {
        view = mockk()
        presenter = ReservationCompletePresenter(view)
    }

    @Test
    fun `영화 티켓을 업데이트 하면 영화 제목, 시간, 좌석, 가격이 보인다`() {
        // given:
        every { view.showTicket(any()) } just Runs

        // when:
        presenter.updateTicketData(MOVIE_TICKET_B1_C3)

        // then:
        verify { view.showTicket(MOVIE_TICKET_B1_C3) }
    }

    @After
    fun finish() {
        clearAllMocks()
    }
}
