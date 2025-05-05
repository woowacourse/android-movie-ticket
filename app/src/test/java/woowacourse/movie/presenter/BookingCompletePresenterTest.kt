package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.bookingcomplete.contract.BookingCompleteContract
import woowacourse.movie.feature.bookingcomplete.presenter.BookingCompletePresenter
import woowacourse.movie.feature.model.BookingInfoUiModel

class BookingCompletePresenterTest {
    private lateinit var presenter: BookingCompletePresenter
    private lateinit var view: BookingCompleteContract.View

    private lateinit var bookingInfoUiModel: BookingInfoUiModel

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingCompletePresenter(view)
        bookingInfoUiModel = BookingInfoUiModel()
    }

    @Test
    fun `prepareBookingInfo 호출 시 티켓 예약 결과를 보여준다`() {
        // given & when
        presenter.prepareBookingInfo(bookingInfoUiModel)

        // then
        verify { view.showBookingResult(bookingInfoUiModel) }
    }

    @Test
    fun `quitBookingInfo 호출 시 뒤로 이동한다`() {
        // given & when
        presenter.quitBookingInfo()

        // then
        verify { view.navigateToBack() }
    }
}
