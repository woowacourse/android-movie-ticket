package movie

import data.SeatPosition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDateTime

internal class SeatSelectionTest {
    @Test
    fun `좌석을 선택하면 선택된 좌석의 수가 증가한다`() {
        val seatSelection = SeatSelection()
        assertThat(seatSelection.sizeOfSelection).isEqualTo(0)
        seatSelection.selectSeat(SeatPosition(1, 1), TicketQuantity(5))
        assertThat(seatSelection.sizeOfSelection).isEqualTo(1)
    }

    @Test
    fun `선택한 좌석들을 반환한다`() {
        val seatSelection = SeatSelection()
        seatSelection.selectSeat(SeatPosition(1, 1), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 2), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 3), TicketQuantity(5))
        assertThat(seatSelection.selection.map { it.position }).containsExactly(
            SeatPosition(1, 1),
            SeatPosition(1, 2),
            SeatPosition(1, 3),
        )
    }

    @Test
    fun `특정 좌석의 선택 여부를 반환한다`() {
        val seatSelection = SeatSelection()
        seatSelection.selectSeat(SeatPosition(1, 1), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 2), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 3), TicketQuantity(5))
        assertAll(
            { assertThat(seatSelection[SeatPosition(1, 0)]).isFalse },
            { assertThat(seatSelection[SeatPosition(1, 1)]).isTrue },
            { assertThat(seatSelection[SeatPosition(1, 2)]).isTrue },
            { assertThat(seatSelection[SeatPosition(1, 3)]).isTrue },
        )
    }

    @Test
    fun `좌석 선택을 다시 선택하면 선택된 좌석의 수가 감소한다`() {
        val seatSelection = SeatSelection()
        seatSelection.selectSeat(SeatPosition(1, 1), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 2), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 3), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 1), TicketQuantity(5))
        assertThat(seatSelection.sizeOfSelection).isEqualTo(2)
    }

    @Test
    fun `선택한 좌석들의 값들을 반환 한다`() {
        val seatSelection = SeatSelection()
        seatSelection.selectSeat(SeatPosition(1, 1), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 2), TicketQuantity(5))
        seatSelection.selectSeat(SeatPosition(1, 3), TicketQuantity(5))
        assertThat(seatSelection.getTotalPrice(LocalDateTime.MIN)).isEqualTo(24000)
    }
}
