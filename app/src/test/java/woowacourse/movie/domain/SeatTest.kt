package woowacourse.movie.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SeatTest {
    @Test
    fun `좌석은 최대 5행으로 구성되어 있다`() {
        // when
        val row = 6
        val column = 3

        // then
        assertThrows<IllegalArgumentException> { Seat(row, column) }
            .shouldHaveMessage("The seats are arranged in 5 rows. But value was $row")
    }

    @Test
    fun `좌석은 최대 4열로 구성되어 있다`() {
        // when
        val row = 4
        val column = 5

        // then
        assertThrows<IllegalArgumentException> { Seat(row, column) }
            .shouldHaveMessage("The seats are arranged in 4 columns. But value was $column")
    }
}
