package woowacourse.movie.presenter

import android.util.Log
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.reservation.MovieReservationContract
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import woowacourse.movie.presentation.reservation.model.TicketModel

class MovieReservationPresenterTest {
    private lateinit var mockView: MockMovieReservationContractView
    private lateinit var presenter: MovieReservationPresenter

    class MockMovieReservationContractView : MovieReservationContract.View {
        var showCurrentResultTicketCountViewCalled = false

        override fun showMovie(movie: Movie) {
            Log.d("showMovie","영화가 보여집니다")
        }

        override fun showCurrentResultTicketCountView() {
            showCurrentResultTicketCountViewCalled = true
        }

        override fun moveToTicketDetail(ticketModel: TicketModel) {
            Log.d("moveToTicketDetail","티켓 디테일 화면으로 이동합니다")
        }

        override fun requestTicketCount(count: (Int) -> Unit) {
            Log.d("requestTicketCount","티켓의 수량을 요청합니다")
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
