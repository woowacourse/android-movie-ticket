package woowacourse.movie.presentation.seat

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import woowacourse.movie.A1_SEAT
import woowacourse.movie.C1_SEAT
import woowacourse.movie.E1_SEAT

class SeatUiModelTest {
    @Test
    fun `B 클래스가 선택되면 10,000원이 책정된다`() {
        SeatUiModel(countThreshold = 1, selectedSeat = listOf(A1_SEAT)).totalPrice shouldBe 10000
    }

    @Test
    fun `A 클래스가 선택되면 10,000원이 책정된다`() {
        SeatUiModel(countThreshold = 1, selectedSeat = listOf(C1_SEAT)).totalPrice shouldBe 12000
    }

    @Test
    fun `S 클래스가 선택되면 10,000원이 책정된다`() {
        SeatUiModel(countThreshold = 1, selectedSeat = listOf(E1_SEAT)).totalPrice shouldBe 15000
    }

    @Test
    fun `최대 선택 좌석 수를 넘어간 상황에서 선택을 하면 null이 리턴된다`() {
        SeatUiModel(countThreshold = 1, selectedSeat = listOf(A1_SEAT))
            .select(E1_SEAT, isSelected = false) shouldBe null
    }

    @Test
    fun `최대 선택 좌석 수를 넘어가지 않은 상황에서 선택하면, 선택과 가격이 적용된다`() {
        SeatUiModel(countThreshold = 2, selectedSeat = listOf(A1_SEAT)).run {
            select(E1_SEAT, isSelected = false)
        }.let {
            it shouldNotBe null
            it?.totalPrice shouldBe (10000 + 15000)
        }
    }

    @Test
    fun `좌석을 선택 해제하면 선택과 가격이 적용된다`() {
        SeatUiModel(countThreshold = 2, selectedSeat = listOf(A1_SEAT, E1_SEAT)).run {
            select(E1_SEAT, isSelected = true)
        }.let {
            it shouldNotBe null
            it?.totalPrice shouldBe 10000
        }
    }
}
