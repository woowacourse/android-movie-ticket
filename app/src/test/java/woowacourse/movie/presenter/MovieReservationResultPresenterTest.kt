package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.fixtures.theater
import woowacourse.movie.fixtures.ticket
import woowacourse.movie.presenter.movieReservationResult.MovieReservationResultContract
import woowacourse.movie.presenter.movieReservationResult.MovieReservationResultPresenter

class MovieReservationResultPresenterTest {
    private lateinit var presenter: MovieReservationResultPresenter
    private lateinit var view: MovieReservationResultContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieReservationResultPresenter(view)
    }

    @Test
    fun `실행 시 예매 완료 정보가 표시된다`() {
        // when
        presenter.onViewCreated(ticket, theater)

        // then
        verifyAll {
            view.showMovieDateTime(any())
            view.showTicketCount(any())
            view.showSelectedSeats(any())
            view.showTotalPrice(any())
        }
    }
}
