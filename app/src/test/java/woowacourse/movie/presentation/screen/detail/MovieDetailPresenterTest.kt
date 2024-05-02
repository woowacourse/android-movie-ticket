package woowacourse.movie.presentation.screen.detail

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Theater
import woowacourse.movie.model.Ticket

@ExtendWith(MockKExtension::class)
class MovieDetailPresenterTest {
    @MockK
    private lateinit var view: MovieDetailContract.View

    private var dao: MovieDao = MovieDao()

    @InjectMockKs
    private lateinit var presenter: MovieDetailPresenter

    @Test
    fun `영화 정보와 극장 정보가 들어오면 view의 setup 함수를 호출한다`() {
        // given
        val movieId = 1
        val movie = dao.find(movieId)
        val theater = Theater(movie)
        val ticket = Ticket()
        every {
            view.setUpView(
                movie.img,
                movie.title,
                movie.screenDateToString(),
                movie.runningTime,
                movie.description,
            )
        } just Runs
        every {
            view.setUpSpinner(
                theater.screenDates(),
                theater.screenTimes(movie.screenDate[0]),
            )
        } just Runs
        every {
            view.updateTicketCount(ticket.count())
        } just Runs
        // when
        presenter.fetchScreenInfo(movieId)
        // then
        verify(exactly = 1) {
            view.setUpView(
                movie.img,
                movie.title,
                movie.screenDateToString(),
                movie.runningTime,
                movie.description,
            )
        }
        verify(exactly = 1) {
            view.setUpSpinner(
                theater.screenDates(),
                theater.screenTimes(movie.screenDate[0]),
            )
        }
        verify(exactly = 1) {
            view.updateTicketCount(ticket.count())
        }
    }

    @Test
    fun `count가 1일 때 subTicketCount를 호출하면 count가 1이다`() {
        // given
        val expectCount = 1
        every { view.updateTicketCount(expectCount) } just Runs

        // when
        presenter.subTicketCount()

        // then
        verify(exactly = 1) { view.updateTicketCount(expectCount) }
    }

    @Test
    fun `count가 1일 때 addTicketCount를 호출하면 count가 2이다`() {
        // given
        val expectCount = 2
        every { view.updateTicketCount(expectCount) } just Runs

        // when
        presenter.addTicketCount()

        // then
        verify(exactly = 1) { view.updateTicketCount(expectCount) }
    }
}
