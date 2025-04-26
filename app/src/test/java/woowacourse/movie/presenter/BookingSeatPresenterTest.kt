package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.MovieSeats
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.domain.model.TicketCount
import woowacourse.movie.feature.bookingseat.contract.BookingSeatContract
import woowacourse.movie.feature.bookingseat.presenter.BookingSeatPresenter
import woowacourse.movie.feature.mapper.toUi
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.SeatSelectionUiState

class BookingSeatPresenterTest {
    private lateinit var presenter: BookingSeatPresenter
    private lateinit var view: BookingSeatContract.View

    private lateinit var bookingInfoUiModel: BookingInfoUiModel

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingSeatPresenter(view)
        bookingInfoUiModel =
            BookingInfo(
                movie =
                    Movie(
                        title = "해리 포터와 마법사의 돌",
                        startDate = MovieDate(2025, 4, 1),
                        endDate = MovieDate(2025, 4, 25),
                        runningTime = 152,
                    ),
                date = MovieDate(2025, 4, 1),
                time = MovieTime(9, 0),
                seats = MovieSeats(),
                ticketCount = TicketCount(),
            ).toUi()
    }

    @Test
    fun `onCreateView 호출 시 좌석과 예약 정보, 가격, 버튼 상태를 표시한다`() {
        // given & when
        presenter.onCreateView(bookingInfoUiModel)

        // then
        verify {
            view.showSeats()
            view.showBookingInfo(bookingInfoUiModel)
            view.updatePrice(any())
            view.updateSeatSelectionCompleteButton(any())
        }
    }

    @Test
    fun `onSeatClicked 호출 시 성공이면 가격 업데이트와 SeatSelectionUiState_Success를 반환한다`() {
        // given
        presenter.onCreateView(bookingInfoUiModel)
        val seat = MovieSeat(1, 1).toUi()

        // when
        val result = presenter.onSeatClicked(seat)

        // then
        verify {
            view.updateSeatSelectionCompleteButton(any())
            view.updatePrice(any())
        }
        assert(result is SeatSelectionUiState.Success)
    }

    @Test
    fun `onSeatSelectionCompleteClicked 호출 시 예약 완료 다이얼로그를 띄운다`() {
        // given & when
        presenter.onSeatSelectionCompleteClicked()

        // then
        verify { view.showBookingCompleteDialog() }
    }

    @Test
    fun `onSeatSelectionCompleteConfirmed 호출 시 예약 완료 화면으로 이동한다`() {
        // given
        presenter.onCreateView(bookingInfoUiModel)

        // when
        presenter.onSeatSelectionCompleteConfirmed()

        // then
        verify { view.navigateToBookingComplete(any()) }
    }

    @Test
    fun `onBackButtonClicked 호출 시 뒤로 이동한다`() {
        // given & when
        presenter.onBackButtonClicked()

        // then
        verify { view.navigateToBack() }
    }
}
