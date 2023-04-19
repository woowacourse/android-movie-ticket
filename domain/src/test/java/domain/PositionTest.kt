package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun `정수 3이 입력되면 좌석 위치는 1행 4열이다`() {
        val position = Position.of(3)

        assertThat(position.row).isEqualTo(1)
        assertThat(position.col).isEqualTo(4)
    }

    @Test
    fun `정수 5가 입력되면 좌석 위치는 2행 2열이다`() {
        val position = Position.of(5)

        assertThat(position.row).isEqualTo(2)
        assertThat(position.col).isEqualTo(2)
    }

    @Test
    fun `정수 10이 입력되면 좌석 위치는 3행 3열이다`() {
        val position = Position.of(10)

        assertThat(position.row).isEqualTo(3)
        assertThat(position.col).isEqualTo(3)
    }

    @Test
    fun `정수 13이 입력되면 좌석 위치는 4행 2열이다`() {
        val position = Position.of(13)

        assertThat(position.row).isEqualTo(4)
        assertThat(position.col).isEqualTo(2)
    }

    @Test
    fun `정수 19가 입력되면 좌석 위치는 5행 4열이다`() {
        val position = Position.of(19)

        assertThat(position.row).isEqualTo(5)
        assertThat(position.col).isEqualTo(4)
    }
}
