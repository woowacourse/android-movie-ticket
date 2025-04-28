package woowacourse.movie.presenter.ticket

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.ticket.TicketContract
import woowacourse.movie.domain.reservation.Seat
import woowacourse.movie.domain.ticket.FakeCancelTimePolicy
import woowacourse.movie.domain.ticket.Ticket
import java.time.LocalDateTime

class TicketPresenterTest {
    private lateinit var view: TicketContract.View
    private lateinit var presenter: TicketContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter =
            TicketPresenter(
                view,
                Ticket(
                    title = "해리 포터와 마법사의 돌",
                    count = 2,
                    showtime = LocalDateTime.of(2025, 4, 15, 11, 0),
                ),
                setOf(Seat(1, 1), Seat(2, 2)),
                FakeCancelTimePolicy(15),
            )
    }

    @Test
    fun `상영 취소 관련 안내를 표시한다`() {
        // given
        every { view.setCancelDescription(15) } just Runs

        // when
        presenter.presentCancelDescription()

        // then
        verify { view.setCancelDescription(15) }
    }

    @Test
    fun `영화 제목을 표시한다`() {
        // given
        every { view.setMovieTitle("해리 포터와 마법사의 돌") } just Runs

        // when
        presenter.presentTitle()

        // then
        verify { view.setMovieTitle("해리 포터와 마법사의 돌") }
    }

    @Test
    fun `영화 상영 시간을 표시한다`() {
        // given
        every { view.setShowtime(LocalDateTime.of(2025, 4, 15, 11, 0)) } just Runs

        // when
        presenter.presentShowtime()

        // then
        verify { view.setShowtime(LocalDateTime.of(2025, 4, 15, 11, 0)) }
    }

    @Test
    fun `예약 인원을 표시한다`() {
        // given
        every { view.setCount(2, setOf(Seat(1, 1), Seat(2, 2))) } just Runs

        // when
        presenter.presentCount()

        // then
        verify { view.setCount(2, setOf(Seat(1, 1), Seat(2, 2))) }
    }

    @Test
    fun `금액을 표시한다`() {
        // given
        every { view.setPrice(26_000) } just Runs

        // when
        presenter.presentPrice()

        // then
        verify { view.setPrice(26_000) }
    }
}
