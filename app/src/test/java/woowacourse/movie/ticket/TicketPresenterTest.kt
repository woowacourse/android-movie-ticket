package woowacourse.movie.ticket

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.ticket.TicketContract
import woowacourse.movie.domain.ticket.Ticket
import woowacourse.movie.presenter.ticket.TicketPresenter
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
            )
    }

    @Test
    fun `영화_제목을_표시한다`() {
        // given
        every { view.setMovieTitle("해리 포터와 마법사의 돌") } just Runs

        // when
        presenter.presentTitle()

        // then
        verify { view.setMovieTitle("해리 포터와 마법사의 돌") }
    }
}
