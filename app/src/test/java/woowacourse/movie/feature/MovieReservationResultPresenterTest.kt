package woowacourse.movie.feature

import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieReservationResult.MovieReservationResultContract
import woowacourse.movie.feature.movieReservationResult.MovieReservationResultPresenter
import woowacourse.movie.fixtures.SEATS_FULL
import woowacourse.movie.fixtures.TICKET

class MovieReservationResultPresenterTest {
    private lateinit var presenter: MovieReservationResultPresenter
    private lateinit var view: MovieReservationResultContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieReservationResultPresenter(view)
    }

    @Test
    fun `initializeReservationInfo 호출 시 예매한 영화 및 좌석 정보와 가격이 표시된다`() {
        // given
        val selectedSeatsText = "A1, A2, A3"

        // when
        presenter.initializeReservationInfo(TICKET, SEATS_FULL)

        // then
        verifyAll {
            view.showReservationInfo(TICKET, selectedSeatsText)
            view.updateTotalPrice(30000)
        }
    }
}
