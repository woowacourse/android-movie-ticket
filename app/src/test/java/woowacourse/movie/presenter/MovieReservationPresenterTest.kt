package woowacourse.movie.presenter

import android.util.Log
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.DateMaker
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.TicketModel
import woowacourse.movie.presentation.reservation.MovieReservationContract
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieReservationPresenterTest {
    private lateinit var mockView: MockMovieReservationContractView
    private lateinit var presenter: MovieReservationPresenter

    class MockMovieReservationContractView : MovieReservationContract.View {
        var showCurrentResultTicketCountViewCalled = false

        override fun showMovie(movie: Movie) {
            Log.d("showMovie", "영화가 보여집니다")
        }

        override fun showCurrentResultTicketCountView() {
            showCurrentResultTicketCountViewCalled = true
        }

        override fun showDate(dates: List<LocalDate>) {
            Log.d("showDate", "날짜가 보여집니다")
        }

        override fun showTime(times: List<LocalTime>) {
            Log.d("showTime", "시간이 보여집니다")
        }

        override fun moveToSeatSelection(pendingMovieReservation: PendingMovieReservationModel) {
            Log.d("moveToTicketDetail", "티켓 디테일 화면으로 이동합니다")
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
                dateMaker = DateMaker(),
            )
    }

    @Test
    fun `티켓을_감소하면_상호작용으로_현재_티켓의_수량을_뷰에_보여지게_한다`() {
        presenter.decreaseTicketCount()
        assertEquals(true, mockView.showCurrentResultTicketCountViewCalled)
    }

    @Test
    fun `티켓을_증가하면_상호작용으로_현재_티켓의_수량을_뷰에_보여지게_한다`() {
        presenter.increaseTicketCount()
        assertEquals(true, mockView.showCurrentResultTicketCountViewCalled)
    }
}
