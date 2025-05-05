package woowacourse.movie.feature.ticket

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TicketPresenterTest {
    private lateinit var ticketView: TicketContract.View
    private lateinit var ticketPresenter: TicketContract.Presenter

    @BeforeEach
    fun setUp() {
        ticketView = mockk(relaxed = true)
        val ticketData: TicketData = mockk(relaxed = true)
        ticketPresenter = TicketPresenter(ticketView, ticketData)
    }

    @Test
    fun `티켓 UI를 초기화한다`() {
        // When
        ticketPresenter.initTicketView()

        // Then
        verify {
            ticketView.setTicketPrice(any())
            ticketView.setTicketCount(any())
            ticketView.setSeatCodes(any())
            ticketView.setShowTime(any())
            ticketView.setMovieTitle(any())
            ticketView.setCancelableMinute(any())
        }
    }
}
