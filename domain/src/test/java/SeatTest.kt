package woowacourse.movie.domain

import org.junit.Assert
import org.junit.Test

class SeatTest {
    @Test
    fun `2행 7열인 좌석을 생성 시 예외가 발생한다`() {
        Assert.assertThrows("[ERROR] 올바른 좌석(열)이 아닙니다.", IllegalArgumentException::class.java) {
            Seat(2, 7)
        }
    }

    @Test
    fun `7행 2열인 좌석을 생성 시 예외가 발생한다`() {
        Assert.assertThrows("[ERROR] 올바른 좌석(행)이 아닙니다.", IllegalArgumentException::class.java) {
            Seat(7, 2)
        }
    }
}
