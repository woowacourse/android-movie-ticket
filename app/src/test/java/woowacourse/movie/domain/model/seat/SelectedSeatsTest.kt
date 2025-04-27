package woowacourse.movie.domain.model.seat

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SelectedSeatsTest {
    private lateinit var selectedSeats: SelectedSeats

    @BeforeEach
    fun setUp() {
        selectedSeats = SelectedSeats(2)
    }

    @Test
    fun `인원 수만큼 좌석을 선택할 수 있다`() {
        // Given
        val seat1 = Seat(SeatPosition(1, 1))
        val seat2 = Seat(SeatPosition(2, 2))

        // When
        selectedSeats.updateSelection(seat1)
        selectedSeats.updateSelection(seat2)

        // Then
        selectedSeats.isFull() shouldBe true
    }

    @Test
    fun `이미 선택된 좌석을 다시 선택할 경우 제거한다`() {
        // Given
        val seat1 = Seat(SeatPosition(1, 1))
        selectedSeats.updateSelection(seat1)

        // When
        selectedSeats.isSelected(seat1) shouldBe true
        selectedSeats.updateSelection(seat1)

        // Then
        selectedSeats.value shouldNotContain seat1
    }

    @Test
    fun `인원 수를 초과하여 좌석을 선택할 수 없다`() {
        // Given
        val seat1 = Seat(SeatPosition(1, 1))
        val seat2 = Seat(SeatPosition(2, 2))
        val seat3 = Seat(SeatPosition(3, 3))

        // When
        selectedSeats.updateSelection(seat1)
        selectedSeats.updateSelection(seat2)

        // Then
        shouldThrow<IllegalArgumentException> { selectedSeats.updateSelection(seat3) }
            .message shouldBe "[ERROR] 더 이상 좌석을 선택할 수 없습니다."
    }

    @Test
    fun `선택된 좌석들의 총 금액을 계산한다`() {
        // Given
        val seat1 = Seat(SeatPosition(1, 1))
        val seat2 = Seat(SeatPosition(2, 2))

        // When
        selectedSeats.updateSelection(seat1)
        selectedSeats.updateSelection(seat2)

        // Then
        selectedSeats.getTotalPrice() shouldBe 25000
    }
}