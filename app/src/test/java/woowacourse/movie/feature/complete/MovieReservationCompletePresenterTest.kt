package woowacourse.movie.feature.complete

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.TicketRepository
import woowacourse.movie.model.data.TicketRepositoryImpl
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
    fun `티켓 데이터를 불러오면 티켓 뷰가 초기화된다`() {
        // given
        every { view.initializeTicket(any()) } just runs

        // when
        val id = ticketRepository.save(ticket1)
        presenter.loadTicketData(id)

        // then
        verify { view.initializeTicket(any()) }
    }

    @Test
    fun `영화 데이터를 불러오면 영화 예매 완료 뷰가 초기화된다`() {
        // given
        every { view.initializeReservationCompleteView(any()) } just runs

        // when
        presenter.loadMovieData(0L)

        // then
        verify { view.initializeReservationCompleteView(any()) }
    }
}
