package woowacourse.movie

import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Ticket
import woowacourse.movie.presenter.DetailContract
import woowacourse.movie.presenter.DetailPresenterImpl

class DetailPresenterImplTest {

    private lateinit var detailView: DetailContract.View
    private lateinit var detailPresenterImpl: DetailPresenterImpl

    @BeforeEach
    fun setUp() {
        detailView = mockk(relaxed = true)
        detailPresenterImpl = DetailPresenterImpl(detailView)
    }

    @Test
    fun testOnMinusButtonClicked() {
        detailPresenterImpl.onMinusButtonClicked(2)
        verify { detailView.updateCounter(1) }
    }

    @Test
    fun testOnPlusButtonClicked() {
        detailPresenterImpl.onPlusButtonClicked(1)
        verify { detailView.updateCounter(2) }
    }

    @Test
    fun testOnReserveButtonClicked() {
        val ticket = mockk<Ticket>()
        detailPresenterImpl.onReserveButtonClicked(ticket)
        verify { detailView.startTicketActivity(ticket) }
    }

    @Test
    fun testCalculateTicketPrice() {
        val ticket = mockk<Ticket>()
        every { ticket.calculateTicketPrice() } returns 10000
        assertThat(ticket.calculateTicketPrice()).isEqualTo(10000)
    }
}
