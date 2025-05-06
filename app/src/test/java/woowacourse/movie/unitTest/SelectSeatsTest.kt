package woowacourse.movie.unitTest

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatSelection
import woowacourse.movie.selectSeat.SeatPrice
import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SelectSeatsTest {
    private val dummyUi =
        TicketUIModel(
            title = "테스트 영화",
            date = "2025.4.1",
            time = "09:00",
            seats = listOf(),
            count = 0,
            money = 0,
        )
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    @Test
    fun `티켓이_올바르게_초기화된다`() {
        val seatSelection = SeatSelection.fromUIModel(dummyUi)

        assertEquals("테스트 영화", seatSelection.titleText)
        assertEquals(LocalDate.of(2025, 4, 1), seatSelection.dateValue)
        assertEquals(LocalTime.of(9, 0), seatSelection.timeValue)
        assertTrue(seatSelection.seats.isEmpty())
        assertEquals(0, seatSelection.money)
    }

    @Test
    fun `날짜와_시간이_정확히_파싱된다`() {
        val parsedDate = SeatSelection.fromUIModel(dummyUi)
        val date = LocalDate.of(2025, 4, 1)
        val time = LocalTime.of(15, 30)

        assertEquals(date, LocalDate.parse(dummyUi.date, dateFormatter))
        assertEquals(time, LocalTime.parse("15:30", timeFormatter))
    }

    @Test
    fun `좌석을_추가하면_목록에_등록되고_금액이_증가한다`() {
        val seatSelection = SeatSelection.fromUIModel(dummyUi)
        val seat = Seat('A', 1)
        val price = SeatPrice.getPrice(seat)

        assertFalse(seatSelection.isSeatSelected(seat))
        seatSelection.selectSeat(seat)
        assertTrue(seatSelection.isSeatSelected(seat))
        assertEquals(price, seatSelection.money)
    }

    @Test
    fun `좌석을_제거하면_목록에서_삭제되고_금액이_감소한다`() {
        val seatSelection = SeatSelection.fromUIModel(dummyUi)
        val seat = Seat('B', 2)
        val price = SeatPrice.getPrice(seat)

        seatSelection.selectSeat(seat)
        assertTrue(seatSelection.isSeatSelected(seat))
        assertEquals(price, seatSelection.money)

        seatSelection.unSelectSeat(seat)
        assertFalse(seatSelection.isSeatSelected(seat))
        assertEquals(0, seatSelection.money)
    }

    @Test
    fun `선택된_좌석_수가_최대치와_일치하면_구매불가로_판단된다`() {
        val seatSelection = SeatSelection.fromUIModel(dummyUi)

        seatSelection.selectSeat(Seat('A', 1))
        seatSelection.selectSeat(Seat('A', 2))
        assertTrue(seatSelection.isFull(2))
        assertFalse(seatSelection.isFull(3))
    }
}
