package woowacourse.movie

import android.widget.TextView
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.activity.seatselection.SeatSelectionContract
import woowacourse.movie.activity.seatselection.SeatSelectionPresenter
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalTime

class SeatSelectionPresenterTest {
    private lateinit var presenter: SeatSelectionPresenter
    private lateinit var view: SeatSelectionContract.View

    private lateinit var dummyTicket: Ticket

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = SeatSelectionPresenter(view)

        dummyTicket =
            Ticket(
                title = "해리 포터와 마법사의 돌",
                date = LocalDate.of(2025, 5, 1),
                time = LocalTime.of(10, 0),
                count = 0,
                money = 0,
                seat = emptyList(),
            )
    }

    @Test
    fun `loadMovie 호출 시 영화 정보를 뷰에 표시한다`() {
        // given & when
        presenter.loadMovie(dummyTicket)

        // then
        verify { view.showMovieInfo(dummyTicket) }
    }

    @Test
    fun `calculateMoney 호출 시 선택된 좌석 가격을 추가한다`() {
        // given & when
        presenter.calculateMoney(rowIndex = 0, isSelected = true)

        // then
        verify { view.showMoney(10000) }
    }

    @Test
    fun `calculateMoney 호출 시 선택 해제된 좌석 가격을 차감한다`() {
        // given
        presenter.calculateMoney(rowIndex = 2, isSelected = true)

        // when
        presenter.calculateMoney(rowIndex = 2, isSelected = false)

        // then
        verify { view.showMoney(0) }
    }

    @Test
    fun `calculateAudienceCount 호출 시 관객 수 증가한다`() {
        // given & when
        val count = presenter.calculateAudienceCount(isSelected = true)

        // then
        assertEquals(1, count)
    }

    @Test
    fun `calculateAudienceCount 호출 시 관객 수 감소한다`() {
        // given
        presenter.calculateAudienceCount(isSelected = true)

        // when
        val count = presenter.calculateAudienceCount(isSelected = false)

        // then
        assertEquals(0, count)
    }

    @Test
    fun `handleConfirmButtonActivation 호출 시 선택된 좌석이 있으면 버튼이 활성화된다`() {
        // given
        val seatSelected = mockk<TextView> { every { isSelected } returns true }
        val seatNotSelected = mockk<TextView> { every { isSelected } returns false }
        val seats = sequenceOf(sequenceOf(seatNotSelected, seatSelected))

        // when
        presenter.handleConfirmButtonActivation(seats)

        // then
        verify { view.updateConfirmButtonState(true) }
    }

    @Test
    fun `handleConfirmButtonActivation 호출 시 선택된 좌석이 없으면 버튼이 비활성화된다`() {
        // given
        val seatNotSelected = mockk<TextView> { every { isSelected } returns false }
        val seats = sequenceOf(sequenceOf(seatNotSelected))

        // when
        presenter.handleConfirmButtonActivation(seats)

        // then
        verify { view.updateConfirmButtonState(false) }
    }

    @Test
    fun `confirmSeatSelection 호출 시 선택된 좌석 리스트와 함께 결과 화면으로 이동한다`() {
        // given
        val seat1 =
            mockk<TextView> {
                every { isSelected } returns true
                every { text } returns "A1"
            }
        val seat2 =
            mockk<TextView> {
                every { isSelected } returns false
                every { text } returns "A2"
            }
        val seats = sequenceOf(sequenceOf(seat1, seat2))

        presenter.calculateAudienceCount(true)
        presenter.calculateMoney(0, true)

        // when
        presenter.confirmSeatSelection(seats, dummyTicket)

        // then
        verify {
            view.moveToBookingResult(
                match {
                    it.count == 1 &&
                        it.money == 10000 &&
                        it.seat.contains("A1") &&
                        it.seat.size == 1
                },
            )
        }
    }
}
