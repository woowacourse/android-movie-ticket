package woowacourse.movie.feature

import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.mockk.verifyAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.movieReservation.MovieReservationContract
import woowacourse.movie.feature.movieReservation.MovieReservationPresenter
import woowacourse.movie.fixtures.MOVIE
import woowacourse.movie.fixtures.TICKET
import java.time.LocalDate

class MovieReservationPresenterTest {
    private lateinit var view: MovieReservationContract.View
    private lateinit var presenter: MovieReservationPresenter

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieReservationPresenter(view)
    }

    @Test
    fun `initializeReservationInfo 호출 시 영화 정보, 상영 날짜와 표 개수를 표시하고, + 버튼과 - 버튼을 갱신한다`() {
        // given
        val dates = slot<List<LocalDate>>()

        // when
        presenter.initializeReservationInfo(MOVIE)

        // then
        verifyAll {
            view.loadSpinnerDates(capture(dates))
            view.showReservationInfo(presenter.ticket)
            view.updateTicketCount(1)
            view.setIncrementEnabled(true)
            view.setDecrementEnabled(false)
        }
        println(dates.captured)
    }

    @Test
    fun `loadReservationInfo 호출 시 저장된 상영 시간을 선택 및 표 개수를 표시하고, + 버튼과 - 버튼을 갱신한다`() {
        // when
        presenter.loadReservationInfo(TICKET)

        // then
        verifyAll {
            view.setTimeSpinner(any())
            view.updateTicketCount(3)
            view.setIncrementEnabled(true)
            view.setDecrementEnabled(true)
        }
    }

    @Test
    fun `selectDate 호출 시 상영 시간의 목록을 갱신한다`() {
        // when
        presenter.initializeReservationInfo(MOVIE)
        presenter.selectDate(TICKET.showtime.toLocalDate())

        // then
        verify {
            view.loadSpinnerTimes(any(), any())
        }
    }

    @Test
    fun `incrementTicketCount 호출 시 표의 개수, + 버튼과 - 버튼을 갱신한다`() {
        // when
        presenter.initializeReservationInfo(MOVIE)
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
        presenter.initializeReservationInfo(MOVIE)
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
        // given
        val ticket = slot<TicketUiModel>()

        // when
        presenter.initializeReservationInfo(MOVIE)
        presenter.confirmSelection()

        // then
        verify { view.goToSeatSelection(capture(ticket)) }
        assertThat(ticket.captured.movie).isEqualTo(MOVIE)
    }
}
