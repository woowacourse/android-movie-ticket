package woowacourse.movie.presentation.bookingsummary

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.pricingpolicy.DefaultPricingPolicy
import woowacourse.movie.domain.model.movie.MovieTicket
import java.time.LocalDateTime

class BookingSummaryPresenterTest {
    private lateinit var view: BookingSummaryContract.View
    private lateinit var presenter: BookingSummaryContract.Presenter

    private val testTicket = MovieTicket(
        title = "test",
        screeningDateTime = LocalDateTime.of(2025, 4, 30, 12, 0),
        headCount = 2,
        pricingPolicy = DefaultPricingPolicy()
    )

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingSummaryPresenter(view, testTicket)
    }

    @Test
    fun `티켓의 정보가 출력된다`() {
        // When
        presenter.onViewCreated()

        // Then
        verify { view.showTicket(testTicket) }
    }
}