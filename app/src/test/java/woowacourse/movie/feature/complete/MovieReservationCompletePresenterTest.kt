package woowacourse.movie.feature.complete

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.TicketRepository
import woowacourse.movie.model.data.TicketRepositoryImpl
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.ticket1

class MovieReservationCompletePresenterTest {
    private lateinit var view: MovieReservationCompleteContract.View
    private val movieRepository: MovieRepository = MovieRepositoryImpl
    private val ticketRepository: TicketRepository = TicketRepositoryImpl
    private lateinit var presenter: MovieReservationCompletePresenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieReservationCompleteContract.View>()
        presenter = MovieReservationCompletePresenter(view, movieRepository, ticketRepository)
    }

    @Test
    fun `티켓 데이터를 불러온다`() {
        // given
        val id = ticketRepository.save(ticket1)
        val ticketSlot = slot<Ticket>()
        every { view.initializeTicket(capture(ticketSlot)) } just runs

        // when
        presenter.loadTicketData(id)

        // then
        val actual = ticketSlot.captured
        assertThat(actual.id).isEqualTo(id)
        verify { view.initializeTicket(actual) }
    }

    @Test
    fun `영화 데이터를 불러온다`() {
        // given
        val movieSlot = slot<Movie>()
        every { view.initializeReservationCompleteView(capture(movieSlot)) } just runs

        // when
        presenter.loadMovieData(0L)

        // then
        val actual = movieSlot.captured
        assertThat(actual.id).isEqualTo(0L)
        verify { view.initializeReservationCompleteView(actual) }
    }
}
