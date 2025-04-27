package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.ui.complete.BookingCompleteContract
import woowacourse.movie.ui.complete.BookingCompletePresenter
import java.time.LocalDateTime

class BookingCompletePresenterTest {
    private lateinit var view: BookingCompleteContract.View
    private lateinit var presenter: BookingCompletePresenter

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingCompletePresenter(view)
        every { view.getBookedTicket() } returns
            BookedTicket(
                "해리 포터",
                Headcount(1),
                LocalDateTime.of(2025, 1, 1, 12, 0),
                Seats().apply { Seat(1, 1) },
            )
    }

    @Test
    fun `티켓의 가격이 뷰에 반영된다`() {
        presenter.updateBookedTicketPrice()
        verify { view.setBookedTicketPriceTextView(any()) }
    }

    @Test
    fun `티켓의 정보가 업데이트되면 뷰에 반영된다`() {
        presenter.updateBookedTicketInfoViews()
        verify { view.setBookedTicketInfoViews(any()) }
    }
}
