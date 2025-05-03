package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.domain.Seat
import woowacourse.movie.seating.SeatingContract
import woowacourse.movie.seating.SeatingPresenter
import java.time.LocalDateTime

class SeatingPresenterTest {
    private lateinit var view: SeatingContract.View
    private lateinit var presenter: SeatingContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = SeatingPresenter(view)

        every { view.showTitle(any()) } just Runs
        every { view.showPrice(any()) } just Runs
        every { view.showDeactivateButton() } just Runs
        every { view.showSeat(any()) } just Runs
        every { view.showActivateButton(any()) } just Runs
    }

    @Test
    fun `set 호출 시 제목과 기본 가격을 보여준다`() {
        val reservationInfo = ReservationInfo("너와 나", LocalDateTime.of(2025,4,30,14,0), 2)

        presenter.set(reservationInfo)

        verify {
            view.showTitle("너와 나")
            view.showPrice("0")
        }
    }

    @Test
    fun `좌석을 다시 클릭하면 선택 해제되고 가격과 버튼 상태 갱신한다`() {
        val reservationInfo = ReservationInfo("영화", LocalDateTime.of(2025, 5, 3, 14, 0), personnel = 2)
        presenter.set(reservationInfo)

        presenter.clickedSeat("A1")
        presenter.clickedSeat("A1")

        verify(exactly = 2) { view.showSeat(any()) }
        verify { view.showDeactivateButton() }
        verify { view.showPrice("0") }
    }

    @Test
    fun `좌석 인원수만큼 선택하면 버튼이 활성화된다`() {
        val reservationInfo = ReservationInfo("영화", LocalDateTime.of(2025, 5, 3, 14, 0), personnel = 2)
        presenter.set(reservationInfo)

        presenter.clickedSeat("A1")
        presenter.clickedSeat("A2")

        verify { view.showActivateButton(any()) }
    }

    @Test
    fun `좌석 인원수 초과로 선택 시 추가 선택되지 않는다`() {
        val reservationInfo = ReservationInfo("영화", LocalDateTime.of(2025, 5, 3, 14, 0), personnel = 1)
        presenter.set(reservationInfo)

        presenter.clickedSeat("A1")
        presenter.clickedSeat("A2")

        verify(exactly = 2) { view.showSeat(match { it.contains(Seat("A1")) }) }
        verify(exactly = 0) { view.showSeat(match { it.contains(Seat("A2")) }) }
    }

    @Test
    fun `isSelected는 선택 여부를 반환한다`() {
        val reservationInfo = ReservationInfo("영화", LocalDateTime.of(2025, 5, 3, 14, 0), personnel = 2)
        presenter.set(reservationInfo)

        presenter.clickedSeat("A1")

        assertTrue(presenter.isSelected("A1"))
    }
}