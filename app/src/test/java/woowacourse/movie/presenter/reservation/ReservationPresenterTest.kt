package woowacourse.movie.presenter.reservation

import io.kotest.core.spec.style.AnnotationSpec.After
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.movie.MovieDate
import woowacourse.movie.model.movie.MovieTime
import woowacourse.movie.presenter.MOVIE

class ReservationPresenterTest {
    private lateinit var presenter: ReservationPresenter
    private lateinit var view: ReservationContract.View
    private lateinit var movieDate: MovieDate
    private lateinit var movieTime: MovieTime

    @BeforeEach
    fun setup() {
        view = mockk()
        presenter = ReservationPresenter(view)
        movieDate = MovieDate(MOVIE.startDate, MOVIE.endDate)
        movieTime = MovieTime()
    }

    @Test
    fun `영화 정보를 업데이트 하면 영화 정보들이 보인다`() {
        // given:
        every { view.showMovieInfo(any()) } just Runs
        every { view.showTicketCount(any()) } just Runs
        every { view.setupDateAdapter(any()) } just Runs
        every { view.updateTimes(any()) } just Runs

        // when:
        presenter.updateMovieData(MOVIE)

        // then:
        verify { view.showMovieInfo(any()) }
        verify { view.showTicketCount(any()) }
        verify { view.setupDateAdapter(any()) }
        verify { view.updateTimes(any()) }
    }

    @Test
    fun `처음 개수가 1이고 티켓 개수를 증가시키면 1 증가된 티켓 개수 2개가 보인다`() {
        // given:
        every { view.showTicketCount(any()) } just Runs

        // when:
        presenter.increaseTicketCount()

        // then:
        verify { view.showTicketCount(2) }
    }

    @Test
    fun `티켓 개수가 2이고 티켓 개수를 감소시키면 1개 감소된 티켓 개수 1개가 보인다`() {
        // given:
        every { view.showTicketCount(any()) } just Runs
        presenter.increaseTicketCount()

        // when:
        presenter.decreaseTicketCount()

        // then:
        verify { view.showTicketCount(1) }
    }

    @Test
    fun `티켓 개수가 1이고 티켓 개수를 감소시키면 토스트 메시지가 보인다`() {
        // given:
        every { view.showTicketCount(any()) } just Runs
        every { view.showErrorToastMessage(any()) } just Runs

        // when:
        presenter.decreaseTicketCount()

        // then:
        verify { view.showErrorToastMessage(any()) }
    }

    @Test
    fun `영화 예매 요청을 보내면 좌석 선택 화면이 보인다`() {
        // given:
        every { view.showSeatSelectionView(any()) } just Runs
        every { view.showMovieInfo(any()) } just Runs
        every { view.showTicketCount(any()) } just Runs
        every { view.setupDateAdapter(any()) } just Runs
        every { view.updateTimes(any()) } just Runs
        every { view.showSeatSelectionView(any()) } just Runs

        presenter.updateMovieData(MOVIE)

        // when:
        presenter.onMovieToReserveRequest()

        // then:
        verify { view.showSeatSelectionView(any()) }
    }

    @Test
    fun `티켓 개수를 업데이트하면 변경된 개수만큼 티켓 개수가 보인다`() {
        // given:
        every { view.showTicketCount(any()) } just Runs

        // when:
        presenter.updateTicketCount(3)

        // then:
        verify { view.showTicketCount(3) }
    }

    @Test
    fun `티켓 개수 업데이트 시 null이 들어오면 티켓 개수가 그대로 보인다`() {
        // given:
        every { view.showTicketCount(any()) } just Runs

        // when:
        presenter.updateTicketCount(null)

        // then:
        verify { view.showTicketCount(1) }
    }

    @Test
    fun `선택한 날짜 포지션을 업데이트하면 선택된 날짜가 보인다`() {
        // given:
        every { view.showSelectedDate(any()) } just Runs

        // when:
        presenter.updateSelectedDatePosition(2)

        // then:
        verify { view.showSelectedDate(2) }
    }

    @Test
    fun `선택한 시간 포지션을 업데이트하면 선택된 시간이 보인다`() {
        // given:
        every { view.showSelectedTime(any()) } just Runs

        // when:
        presenter.updateSelectedTimePosition(1)

        // then:
        verify { view.showSelectedTime(1) }
    }

    @After
    fun finish() {
        clearAllMocks()
    }
}
