package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GradeTest {

    @Test
    fun `B등급일 때, 가격은 10000원이다`() {
        val grade = Grade.B

        assertThat(grade.ticketPrice).isEqualTo(10_000)
    }

    @Test
    fun `S등급일 때, 가격은 15000원이다`() {
        val grade = Grade.S

        assertThat(grade.ticketPrice).isEqualTo(15_000)
    }

    @Test
    fun `A등급일 때, 가격은 12000원이다`() {
        val grade = Grade.A

        assertThat(grade.ticketPrice).isEqualTo(12_000)
    }
}
