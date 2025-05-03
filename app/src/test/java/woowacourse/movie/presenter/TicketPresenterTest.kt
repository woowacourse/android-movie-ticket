package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.ticket.TicketPresenter
import woowacourse.movie.feature.ticket.TicketView

class TicketPresenterTest {
    private lateinit var ticketView: TicketView
    private lateinit var ticketPresenter: TicketPresenter

    @BeforeEach
    fun setUp() {
        ticketView = mockk(relaxed = true)
        ticketPresenter = TicketPresenter(ticketView)
    }

    @Test
    fun `티켓 UI를 초기화한다`() {
        // When
        ticketPresenter.initTicketUi()

        // Then
        verify { ticketView.initTicketUI(any()) }
    }
}
