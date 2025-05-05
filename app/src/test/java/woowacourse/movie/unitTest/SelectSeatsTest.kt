package woowacourse.movie.unitTest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import woowacourse.movie.model.SelectSeats
import woowacourse.movie.selectSeat.SeatPrice
import java.time.LocalDate
import java.time.LocalTime

class SelectSeatsTest {
    private var subject: SelectSeats = SelectSeats()

    private val dummyUi =
        woowacourse.movie.uiModel.TicketUIModel(
            title = "테스트 영화",
            date = "2025.4.1",
            time = "09:00",
            seats = listOf(),
            count = 0,
            money = 0,
        )

    @Test
    fun `티켓이_올바르게_초기화된다`() {
        subject.setTicket(dummyUi)

        val t = subject.seatSelection
        assertEquals("테스트 영화", t.title)
        assertEquals(LocalDate.of(2025, 4, 1), t.date)
        assertEquals(LocalTime.of(9, 0), t.time)
        assertTrue(t.seats.isEmpty())
        assertEquals(0, t.money)
    }

    @Test
    fun `날짜와_시간이_정확히_파싱된다`() {
        assertEquals(LocalDate.of(2025, 4, 1), subject.parserDate("2025.4.1"))
        assertEquals(LocalTime.of(15, 30), subject.parserTime("15:30"))
    }

    @Test
    fun `좌석을_추가하면_목록에_등록되고_금액이_증가한다`() {
        subject.setTicket(dummyUi)
        val price = SeatPrice.getPrice("A1")

        assertFalse(subject.isSeatSelected("A1"))
        subject.selectSeat("A1")
        assertTrue(subject.isSeatSelected("A1"))
        assertEquals(price, subject.seatSelection.money)
    }

    @Test
    fun `좌석을_제거하면_목록에서_삭제되고_금액이_감소한다`() {
        subject.setTicket(dummyUi)
        val price = SeatPrice.getPrice("B2")

        subject.selectSeat("B2")
        assertTrue(subject.isSeatSelected("B2"))
        assertEquals(price, subject.seatSelection.money)

        subject.unSelectSeat("B2")
        assertFalse(subject.isSeatSelected("B2"))
        assertEquals(0, subject.seatSelection.money)
    }

    @Test
    fun `선택된_좌석_수가_최대치와_일치하면_구매불가로_판단된다`() {
        subject.setTicket(dummyUi)

        subject.selectSeat("A1")
        subject.selectSeat("A2")
        assertTrue(subject.isFullSeat(2))
        assertFalse(subject.isFullSeat(3))
    }
}
