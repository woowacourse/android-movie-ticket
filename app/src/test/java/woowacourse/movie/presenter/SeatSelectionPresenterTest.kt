package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Count
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.theater.TheaterSize
import woowacourse.movie.model.ticketing.BookingDateTime
import woowacourse.movie.model.ticketing.BookingSeat
import woowacourse.movie.presenter.contract.SeatSelectionContract
import woowacourse.movie.view.state.TicketingForm
import woowacourse.movie.view.state.TicketingResult
import woowacourse.movie.view.utils.ErrorMessage
import java.time.LocalDate
import java.time.LocalTime

class SeatSelectionPresenterTest {
    private lateinit var view: SeatSelectionContract.View
    private lateinit var presenter: SeatSelectionContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<SeatSelectionContract.View>()
        presenter = SeatSelectionPresenter(view)
    }

    @Test
    fun `예매_신청_정보에_따라_좌석_정보를_표출한다`() {
        // given
        every { view.initializeSeatTable(any(), any(), any(), any(), any()) } just runs
        // when
        val ticketingForm =
            TicketingForm(
                0L,
                "해리 포터와 마법사의 돌",
                Count(1),
                BookingDateTime(
                    LocalDate.of(2024, 3, 1),
                    LocalTime.of(11, 0),
                ),
            )
        presenter.loadSeats(ticketingForm, emptyList())
        // then
        verify {
            view.initializeSeatTable(
                TheaterSize(5, 4),
                mapOf(
                    1 to SeatClass.B,
                    2 to SeatClass.B,
                    3 to SeatClass.S,
                    4 to SeatClass.S,
                    5 to SeatClass.A,
                ),
                "해리 포터와 마법사의 돌",
                0,
                emptyList(),
            )
        }
    }

    @Test
    fun `유효하지_않은_상영_아이디를_제공하면_좌석_정보를_표출하지_못하고_토스트_메시지를_출력한다`() {
        // given
        every { view.showToastMessage(any()) } just runs
        // when
        val ticketingForm =
            TicketingForm(
                -1L,
                "해리 포터와 마법사의 돌",
                Count(1),
                BookingDateTime(
                    LocalDate.of(2024, 3, 1),
                    LocalTime.of(11, 0),
                ),
            )
        presenter.loadSeats(ticketingForm, emptyList())
        // then
        verify {
            view.showToastMessage(ErrorMessage.ERROR_INVALID_SCREENING_ID)
        }
    }

    @Test
    fun `좌석_정보를_업데이트하면_좌석_뷰의_배경색이_변경된다`() {
        // given
        every { view.initializeSeatTable(any(), any(), any(), any(), any()) } just runs
        every { view.toggleSeat(any(), any(), any(), any(), any()) } just runs
        every { view.updateTotalPrice(any()) } just runs
        every { view.updateButtonStatus(any()) } just runs
        // when
        val ticketingForm =
            TicketingForm(
                0L,
                "해리 포터와 마법사의 돌",
                Count(1),
                BookingDateTime(
                    LocalDate.of(2024, 3, 1),
                    LocalTime.of(11, 0),
                ),
            )
        presenter.loadSeats(ticketingForm, emptyList())
        presenter.updateSeat(1, 1, SeatClass.B, 4)
        // then
        verify {
            view.initializeSeatTable(
                TheaterSize(5, 4),
                mapOf(
                    1 to SeatClass.B,
                    2 to SeatClass.B,
                    3 to SeatClass.S,
                    4 to SeatClass.S,
                    5 to SeatClass.A,
                ),
                "해리 포터와 마법사의 돌",
                0,
                emptyList(),
            )
            view.toggleSeat(1, 1, SeatClass.B, any(), 4)
            view.updateTotalPrice(10000)
            view.updateButtonStatus(any())
        }
    }

    @Test
    fun `예약_인원을_초과하여_좌석을_선택하면_좌석_뷰의_배경색이_변경되지 않는다`() {
        // given
        every { view.initializeSeatTable(any(), any(), any(), any(), any()) } just runs
        every { view.showToastMessage(any()) } just runs
        // when
        val ticketingForm =
            TicketingForm(
                0L,
                "해리 포터와 마법사의 돌",
                Count(1),
                BookingDateTime(
                    LocalDate.of(2024, 3, 1),
                    LocalTime.of(11, 0),
                ),
            )
        presenter.loadSeats(ticketingForm, listOf(BookingSeat(1, 2, SeatClass.B)))
        presenter.updateSeat(1, 1, SeatClass.B, 4)
        // then
        verify {
            view.initializeSeatTable(any(), any(), any(), any(), any())
            view.showToastMessage(ErrorMessage.ERROR_OVER_COUNT)
        }
    }

    @Test
    fun `예약을_완료하면_결과_화면으로_이동한다`() {
        // given
        every { view.initializeSeatTable(any(), any(), any(), any(), any()) } just runs
        every { view.toggleSeat(any(), any(), any(), any(), any()) } just runs
        every { view.updateTotalPrice(any()) } just runs
        every { view.updateButtonStatus(any()) } just runs
        every { view.navigateToResultScreen(any()) } just runs
        // when
        val ticketingForm =
            TicketingForm(
                0L,
                "해리 포터와 마법사의 돌",
                Count(1),
                BookingDateTime(
                    LocalDate.of(2024, 3, 1),
                    LocalTime.of(11, 0),
                ),
            )
        presenter.loadSeats(ticketingForm, emptyList())
        presenter.updateSeat(1, 1, SeatClass.B, 4)
        presenter.makeReservation()

        // then
        val ticketingResult =
            TicketingResult(
                "해리 포터와 마법사의 돌",
                1,
                LocalDate.of(2024, 3, 1),
                LocalTime.of(11, 0),
                listOf(BookingSeat(1, 1, SeatClass.B)),
                10000,
            )
        verify {
            view.initializeSeatTable(any(), any(), any(), any(), any())
            view.toggleSeat(any(), any(), any(), any(), any())
            view.updateTotalPrice(any())
            view.updateButtonStatus(true)
            view.navigateToResultScreen(ticketingResult)
        }
    }
}
