package woowacourse.movie.presentation.booking

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenterTest {
    private lateinit var view: BookingContract.View
    private lateinit var presenter: BookingContract.Presenter

    private val testMovie = Movie(
        "test",
        LocalDate.of(2025, 4, 29),
        LocalDate.of(2025, 4, 30),
        100
    )

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingPresenter(view, testMovie)
    }

    @Test
    fun `영화의 정보와 예매 가능 날짜, 인원 수가 화면에 출력된다`() {
        // Given
        val expected = listOf(
            LocalDate.of(2025, 4, 29),
            LocalDate.of(2025, 4, 30)
        )

        // When
        presenter.onViewCreated()

        // Then
        verify { view.initBooking() }
        verify { view.showMovie(testMovie) }
        verify { view.showBookableDates(expected) }
        verify { view.updateHeadCount(1) }
    }

    @Test
    fun `날짜를 선택하면 예약 가능한 시간들이 출력된다`() {
        // Given
        val date = LocalDate.of(2025, 4, 29)

        // When
        presenter.onDateSelected(date)

        // Then
        verify { view.showBookableTimes(any()) }
    }

    @Test
    fun `증가 버튼을 누르면 예매 인원을 증가시키고 출력한다`() {
        // When
        presenter.onIncreaseHeadCount()

        // Then
        verify { view.updateHeadCount(2) }
    }

    @Test
    fun `현재 인원이 2 이상일 때 감소 버튼을 누르면 예매 인원을 감소시키고 출력한다`() {
        // Given
        presenter.onIncreaseHeadCount()

        // When
        presenter.onDecreaseHeadCount()

        // Then
        verify { view.updateHeadCount(1) }
    }

    @Test
    fun `예매 버튼을 누르면 티켓을 생성하고 화면을 이동한다`() {
        // Given
        val date = LocalDate.of(2025, 4, 29)
        val time = LocalTime.of(12, 0)
        presenter.onDateSelected(date)
        presenter.onTimeSelected(time)

        // When
        presenter.onConfirmClicked()

        // Then
        verify {
            view.navigateToBookingSummary(
                match {
                    it.title == testMovie.title &&
                    it.screeningDateTime == LocalDateTime.of(date, time) &&
                    it.headCount == 1
                }
            )
        }
    }
}