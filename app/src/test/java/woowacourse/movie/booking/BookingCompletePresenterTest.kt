package woowacourse.movie.booking

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.booking.complete.BookingCompleteContract
import woowacourse.movie.booking.complete.BookingCompletePresenter
import woowacourse.movie.model.BookingResult
import java.time.LocalDate
import java.time.LocalTime

class BookingCompletePresenterTest {
    private lateinit var presenter: BookingCompletePresenter
    private lateinit var mockView: BookingCompleteContract.View
    private lateinit var mockBookingResult: BookingResult

    @BeforeEach
    fun setUp() {
        mockView = mockk(relaxed = true)
        mockBookingResult =
            BookingResult(
                title = "해리 포터와 마법사의 돌",
                headCount = 3,
                selectedDate = LocalDate.of(2028, 10, 13),
                selectedTime = LocalTime.of(11, 0),
            )

        presenter = BookingCompletePresenter(view = mockView, bookingResult = mockBookingResult)
    }

    @Test
    fun `영화 예매 정보가 null이면 오류 메시지를 띄우고 종료한다`() {
        presenter = BookingCompletePresenter(view = mockView, bookingResult = null)

        presenter.initializeData(null)

        verify { mockView.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.") }
        confirmVerified(mockView)
    }

    @Test
    fun `영화 예매 정보를 화면에 표시할 수 있다`() {
        presenter = BookingCompletePresenter(view = mockView, bookingResult = mockBookingResult)

        presenter.initializeData(null)

        verify { mockView.showBookingCompleteResult(mockBookingResult) }
    }
}
