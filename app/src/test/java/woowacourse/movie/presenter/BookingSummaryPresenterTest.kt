package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.ui.view.booking.BookingSummaryContract

class BookingSummaryPresenterTest {
    private lateinit var view: BookingSummaryContract.View
    private lateinit var presenter: BookingSummaryPresenter

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingSummaryPresenter(view)
    }

    @Test
    fun `loadBookingSummary 함수가 호출되며 예매 정보가 나타난다`() {
        // given
        every { view.showBookingSummary() } just runs

        // when
        presenter.loadBookingSummary()

        // then
        verify {
            view.showBookingSummary()
        }
    }
}
