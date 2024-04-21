package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.reservation.MovieReservationContract
import woowacourse.movie.presentation.reservation.MovieReservationPresenter

class TicketCounterPresenterTest {
    private lateinit var mockView: MockMovieReservationContractView
    private lateinit var presenter: MovieReservationPresenter

    class MockMovieReservationContractView : MovieReservationContract.View {
        var showCurrentResultTicketCountViewCalled = false
        override fun showMovie(movie: Movie) {
            TODO("Not yet implemented")
        }

        override fun showCurrentResultTicketCountView() {
            showCurrentResultTicketCountViewCalled = true
        }
    }

    @BeforeEach
    fun setup() {
        mockView = MockMovieReservationContractView()
        presenter = MovieReservationPresenter(
            view = mockView,
            movieRepository = MovieRepositoryImpl()
        )
    }

    @Test
    fun `마이너스 버튼을 클릭하면 showCurrentResultTicketCountView()를 호출해야 한다`() {
        presenter.clickMinusNumberButton()
        assertEquals(true, mockView.showCurrentResultTicketCountViewCalled)
    }

    @Test
    fun `플러스 버튼을 클릭하면 showCurrentResultTicketCountView()를 호출해야 한다`() {
        presenter.clickPlusNumberButton()
        assertEquals(true, mockView.showCurrentResultTicketCountViewCalled)
    }
}
