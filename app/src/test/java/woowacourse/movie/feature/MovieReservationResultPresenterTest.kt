package woowacourse.movie.feature

import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieReservationResult.MovieReservationResultContract
import woowacourse.movie.feature.movieReservationResult.MovieReservationResultPresenter
import woowacourse.movie.fixtures.seats
import woowacourse.movie.fixtures.ticket

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
        presenter.loadReservationInfo(ticket, seats)

        // then
        verifyAll {
            view.showMovieTitle(any())
            view.showMovieDateTime(any())
            view.showTicketCount(any())
            view.showSelectedSeats(any())
            view.showTotalPrice(any())
        }
    }
}
