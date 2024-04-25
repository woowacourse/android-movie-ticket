package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SeatingSystemTest {
    private lateinit var seatingSystem: SeatingSystem

    @BeforeEach
    fun setup() {
        seatingSystem = SeatingSystem()
    }

    @Test
    fun `전체 좌석은 5행 4열로 구성되어 있다`() {
        val seats = seatingSystem.seats
        assertThat(seats.size).isEqualTo(5 * 4)
    }
}
