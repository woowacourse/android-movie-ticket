package woowacourse.movie.presentation.reservation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.model.Movie

class MovieReservationPresenterTest {
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
        presenter =
            MovieReservationPresenter(
                view = mockView,
                movieId = 1,
                movieRepository = MovieRepositoryImpl(),
            )
    }

    @Test
    fun `decreaseTicketCount과_상호작용으로_showCurrentResultTicketCountView를_호출해야_한다`() {
        presenter.decreaseTicketCount()
        assertEquals(true, mockView.showCurrentResultTicketCountViewCalled)
    }

    @Test
    fun `increaseTicketCount과_상호작용으로_showCurrentResultTicketCountView를_호출해야_한다`() {
        presenter.increaseTicketCount()
        assertEquals(true, mockView.showCurrentResultTicketCountViewCalled)
    }
}
