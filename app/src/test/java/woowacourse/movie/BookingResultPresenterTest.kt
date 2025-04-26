package woowacourse.movie

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.activity.bookingresult.BookingResultContract
import woowacourse.movie.activity.bookingresult.BookingResultPresenter
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalTime

class BookingResultPresenterTest {
    private lateinit var presenter: BookingResultPresenter
    private lateinit var view: BookingResultContract.View

    private lateinit var dummyTicket: Ticket

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingResultPresenter(view)

        dummyTicket =
            Ticket(
                title = "해리 포터와 마법사의 돌",
                date = LocalDate.of(2025, 5, 1),
                time = LocalTime.of(10, 0),
                count = 0,
                money = 0,
                seat = emptyList(),
            )
    }

    @Test
    fun `loadTicket 호출 시 티켓 정보를 뷰에 표시한다`() {
        // given & when
        presenter.loadTicket(dummyTicket)

        // then
        verify { view.showTicketInfo(dummyTicket) }
    }
}
