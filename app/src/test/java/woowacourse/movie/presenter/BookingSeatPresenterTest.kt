package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.ui.seat.BookingSeatContract
import woowacourse.movie.ui.seat.BookingSeatPresenter

class BookingSeatPresenterTest {
    private lateinit var view: BookingSeatContract.View
    private lateinit var presenter: BookingSeatPresenter

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        every { view.getHeadcount() } returns Headcount(2)
        every { view.getMovieTitle() } returns "해리 포터와 마법사의 돌"
        presenter = BookingSeatPresenter(view)
    }

    @Test
    fun `가격을 업데이트하면 뷰에 반영된다`() {
        presenter.updateTotalPrice()
        verify { view.setTotalPriceTextView(any()) }
    }

    @Test
    fun `제목을 업데이트하면 뷰에 반영된다`() {
        presenter.updateMovieTitle()
        verify { view.setMovieTitleTextView(any()) }
    }

    @Test
    fun `확인 버튼을 업데이트하면 활성화되거나 비활성화된다`() {
        presenter.updateConfirmButton()
        verify { view.setConfirmButton(any()) }
    }

    @Test
    fun `좌석을 선택하면 좌석이 추가되고 View가 업데이트 된다`() {
        presenter.updateSeat("A1")
        verify { view.selectSeat(0 to 0) }
        verify { view.setTotalPriceTextView(any()) }
        verify { view.setConfirmButton(any()) }
    }

    @Test
    fun `선택한 좌석 수가 인원 수와 다르면 확인 버튼이 비활성화된다`() {
        presenter.updateSeat("A1")
        // Headcount는 2이지만, 좌석이 아직 하나만 선택되었다.
        verify { view.setConfirmButton(false) }
    }

    @Test
    fun `선택한 좌석 수가 인원 수와 같으면 확인 버튼이 활성화된다`() {
        presenter.updateSeat("A1")
        presenter.updateSeat("A2")
        verify { view.setConfirmButton(true) }
    }
}
