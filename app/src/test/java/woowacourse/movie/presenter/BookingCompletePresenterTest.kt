package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.ui.complete.contract.BookingCompleteContract
import woowacourse.movie.ui.complete.presenter.BookingCompletePresenter
import java.time.LocalDateTime

class BookingCompletePresenterTest {
    private lateinit var view: BookingCompleteContract.View
    private lateinit var presenter: BookingCompletePresenter

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingCompletePresenter(view)
        val fakeBookedTicket =
            BookedTicket(
                movieName = "해리 포터와 마법사의 돌",
                dateTime = LocalDateTime.of(2025, 5, 3, 20, 0),
                headcount = Headcount(2),
                seats = Seats(),
            )
        presenter.loadBookedTicket(fakeBookedTicket)
    }

    @Test
    fun `티켓의 가격이 뷰에 반영된다`() {
        presenter.refreshTicketPrice()
        verify { view.setBookedTicketPrice(any()) }
    }

    @Test
    fun `티켓의 정보가 업데이트되면 뷰에 반영된다`() {
        presenter.refreshBookedTicketDisplay()
        verify { view.setBookedTicket(any()) }
    }
}
