package woowacourse.movie.feature

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieReservation.MovieReservationContract
import woowacourse.movie.feature.movieReservation.MovieReservationPresenter
import woowacourse.movie.fixtures.movie

class MovieReservationPresenterTest {
    private lateinit var presenter: MovieReservationPresenter
    private lateinit var view: MovieReservationContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieReservationPresenter(view)
    }

    @Test
    fun `실행 시 예매 정보를 표시한다`() {
        // when
        presenter.loadReservationInfo(movie)

        // then
        verifyAll {
            view.loadSpinnerDates(any())
            view.showReservationInfo(any())
            view.updateTicketCount(any())
            view.setIncrementEnabled(any())
            view.setDecrementEnabled(any())
        }
    }

    @Test
    fun `예매 완료 시 좌석 선택 화면으로 이동한다`() {
        // given
        presenter.loadReservationInfo(movie)
        every { view.goToSeatSelection(any()) } just Runs

        // when
        presenter.confirmSelection()

        // then
        verify { view.goToSeatSelection(any()) }
    }
}
