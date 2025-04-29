package woowacourse.movie

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.activity.booking.BookingContract
import woowacourse.movie.activity.booking.BookingPresenter
import woowacourse.movie.domain.Movie
import java.time.Duration
import java.time.LocalDate

class BookingPresenterTest {
    private lateinit var presenter: BookingPresenter
    private lateinit var view: BookingContract.View

    private lateinit var dummyMovie: Movie

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingPresenter(view)

        dummyMovie =
            Movie(
                title = "해리 포터와 마법사의 돌",
                startDate = LocalDate.of(2025, 5, 1),
                endDate = LocalDate.of(2025, 5, 10),
                runningTime = Duration.ofMinutes(152),
            )
    }

    @Test
    fun `initData 호출 시 페이지 세팅 및 날짜, 시간, 티켓 수가 갱신된다`() {
        // given & when
        presenter.initData(dummyMovie)

        // then
        verify {
            view.setupPage(any())
            view.updateDateSpinner(any(), any())
            view.updateTimeSpinner(any(), any())
            view.showTicketCount(any())
        }
    }

    @Test
    fun `increaseTicketCount 호출 시 티켓 수 증가 후 뷰 업데이트된다`() {
        // given
        presenter.initData(dummyMovie)

        // when
        presenter.increaseTicketCount()

        // then
        verify { view.showTicketCount(1) }
    }

    @Test
    fun `decreaseTicketCount 호출 시 티켓 수 감소 후 뷰 업데이트된다`() {
        // given
        presenter.initData(dummyMovie)
        presenter.increaseTicketCount() // 티켓 1장 추가

        // when
        presenter.decreaseTicketCount()

        // then
        verify { view.showTicketCount(0) }
    }

    @Test
    fun `confirmBooking 호출 시 티켓 수가 1 이상이면 좌석 선택 화면으로 이동한다`() {
        // given
        presenter.initData(dummyMovie)
        presenter.increaseTicketCount() // 티켓 1장 추가

        // when
        presenter.confirmBooking()

        // then
        verify { view.moveToSeatSelection(any()) }
    }

    @Test
    fun `confirmBooking 호출 시 티켓 수가 0이면 아무 동작 안 한다`() {
        // given
        presenter.initData(dummyMovie)

        // when
        presenter.confirmBooking()

        // then
        verify(exactly = 0) { view.moveToSeatSelection(any()) }
    }

    @Test
    fun `selectDate 호출 시 선택한 날짜에 맞는 시간 리스트를 갱신한다`() {
        // given
        presenter.initData(dummyMovie)

        // when
        presenter.selectDate(0)

        // then
        verify { view.updateTimeSpinner(any(), 0) }
    }

    @Test
    fun `selectTime 호출 시 시간 포지션을 저장한다`() {
        // given
        presenter.initData(dummyMovie)

        // when
        presenter.selectTime(1)

        // then
        assertEquals(1, presenter.getSelectedTime())
    }
}
