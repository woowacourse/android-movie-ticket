package woowacourse.movie.feature

import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieReservation.MovieReservationContract
import woowacourse.movie.feature.movieReservation.MovieReservationPresenter
import woowacourse.movie.fixtures.MOVIE
import woowacourse.movie.fixtures.TICKET

class MovieReservationPresenterTest {
    private lateinit var view: MovieReservationContract.View
    private lateinit var presenter: MovieReservationPresenter

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieReservationPresenter(view)
    }

    @Test
    fun `loadReservationInfo 호출 시 예매 정보를 표시한다`() {
        // when
        presenter.loadReservationInfo(MOVIE)

        // then
        verifyAll {
            view.loadSpinnerDates(any())
            view.showReservationInfo(presenter.ticket)
            view.updateTicketCount(1)
            view.setIncrementEnabled(true)
            view.setDecrementEnabled(false)
        }
    }

    @Test
    fun `restoreReservationInfo 호출 시 시간을 선택하고 예매 정보를 표시한다`() {
        // when
        presenter.restoreReservationInfo(TICKET)

        // then
        verifyAll {
            view.setTimeSpinner(any())
            view.updateTicketCount(3)
            view.setIncrementEnabled(true)
            view.setDecrementEnabled(true)
        }
    }

    @Test
    fun `selectDate 호출 시 상영 시간 정보의 목록을 갱신한다`() {
        // when
        presenter.loadReservationInfo(MOVIE)
        presenter.selectDate(TICKET.showtime.toLocalDate())

        // then
        verify {
            view.loadSpinnerTimes(any(), any())
        }
    }

    @Test
    fun `incrementTicketCount 호출 시 표의 개수, + 버튼과 - 버튼을 갱신한다`() {
        // when
        presenter.loadReservationInfo(MOVIE)
        presenter.incrementTicketCount()

        // then
        verify {
            view.updateTicketCount(2)
            view.setIncrementEnabled(true)
            view.setDecrementEnabled(true)
        }
    }

    @Test
    fun `decrementTicketCount 호출 시 표의 개수, + 버튼과 - 버튼을 갱신한다`() {
        // when
        presenter.loadReservationInfo(MOVIE)
        presenter.decrementTicketCount()

        // then
        verify {
            view.updateTicketCount(1)
            view.setIncrementEnabled(true)
            view.setDecrementEnabled(false)
        }
    }

    @Test
    fun `confirmSelection 호출 시 좌석 선택 화면으로 이동한다`() {
        // when
        presenter.loadReservationInfo(MOVIE)
        presenter.confirmSelection()

        // then
        verify { view.goToSeatSelection(presenter.ticket) }
    }
}
