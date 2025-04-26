package woowacourse.movie.view.reserve

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.HARRY_POTTER_MOVIE
import woowacourse.movie.LAST_MOVIE_TIME
import woowacourse.movie.MAY_FIRST
import woowacourse.movie.ui.reserve.ReserveContract
import woowacourse.movie.ui.reserve.ReservePresenter
import java.time.LocalDate
import java.time.LocalDateTime

class ReservePresenterTest {
    private val view = mockk<ReserveContract.View>(relaxed = true)
    private val presenter = ReservePresenter(view)

    private val mockMovie = HARRY_POTTER_MOVIE
    private val mockDateTime: () -> LocalDateTime =
        { LocalDateTime.of(MAY_FIRST, LAST_MOVIE_TIME) }
    private val mockDates = listOf(LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 2))
    private val mockTimes = listOf(LAST_MOVIE_TIME)
    private val increasedPurchaseCount = 2
    private val decreasedPurchaseCountAfterIncrease = 1

    @BeforeEach
    fun setUp() {
        presenter.initMovie(mockMovie)
        presenter.initReservationData(mockDateTime)
    }

    @Test
    fun `initMovie 호출 시 view의 initScreen에 Movie 객체가 전달된다`() {
        verify { view.initScreen(mockMovie) }
    }

    @Test
    fun `initDateSpinner 호출 시 view의 fetchDates에 예상되는 날짜 목록이 전달된다`() {
        presenter.initDateSpinner(MAY_FIRST)

        verify { view.fetchDates(mockDates) }
    }

    @Test
    fun `initTimeSpinner 호출 시 view의 fetchTimes에 특정 날짜에 대한 예상 시간 목록이 전달된다`() {
        presenter.initTimeSpinner(
            MAY_FIRST,
            LocalDateTime.of(MAY_FIRST, LAST_MOVIE_TIME),
        )

        verify { view.fetchTimes(mockTimes) }
    }

    @Test
    fun `increasePurchaseCount 호출 시 view의 fetchPurchaseCount에 증가된 구매 수량이 전달된다`() {
        presenter.increasePurchaseCount()

        verify { view.fetchPurchaseCount(increasedPurchaseCount) }
    }

    @Test
    fun `decreasePurchaseCount 호출 후 view의 fetchPurchaseCount에 감소된 구매 수량이 전달된다`() {
        presenter.increasePurchaseCount()
        presenter.decreasePurchaseCount()

        verify { view.fetchPurchaseCount(decreasedPurchaseCountAfterIncrease) }
    }

    @Test
    fun `dateOnClick 호출 시 view의 dateOnClick에 정확한 Date와 예상되는 상영 시간 크기가 전달된다`() {
        presenter.dateOnClick(MAY_FIRST, LocalDateTime.of(MAY_FIRST, LAST_MOVIE_TIME), mockDateTime)

        verify {
            view.fetchTimes(listOf(LAST_MOVIE_TIME))
            view.dateOnClick(
                MAY_FIRST,
                1,
            )
        }
    }

    @Test
    fun `timeOnClick 호출 시 view의 timeOnClick에 정확한 Position이 전달된다`() {
        // Given
        val selectedPosition = 1

        // When
        presenter.timeOnClick(selectedPosition, mockDateTime)

        // Then
        verify { view.timeOnClick(selectedPosition) }
    }
}
