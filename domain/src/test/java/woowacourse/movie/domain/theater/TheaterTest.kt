package woowacourse.movie.domain.theater

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class TheaterTest {

    @Test
    fun `극장의 아이디가 설정된 상태에서 아이디를 다시 변경하면 변경되지 않는다`() {
        val theater = Theater(4, 5)
        theater.id = 1L

        theater.id = 2L

        assertThat(theater.id).isEqualTo(1L)
    }

    @Test
    fun `극장을 생성할 때 좌석의 행 수 또는 열 수가 양수가 아니면 에러가 발생한다`() {
        val seatRows = 0
        val seatColumns = 1

        assertThatIllegalArgumentException().isThrownBy { Theater(seatRows, seatColumns) }
            .withMessage("좌석들의 행과 열의 개수는 양수여야 합니다.")
    }

    @Test
    fun `극장을 생성하면 1행 1열부터 입력된 행의 개수행 입력된 열의 개수열까지 좌석이 생성된다`() {
        val seatRows = 5
        val seatColumns = 4
        val theater = Theater(seatRows, seatColumns)

        assertAll(
            { assertThat(theater.seats.keys).hasSize(20) },
            { assertThat(theater.seats.keys).contains(Point(1, 1), Point(seatRows, seatColumns)) }
        )
    }

    @Test
    fun `특정 좌표에 좌석이 존재하는지 알 수 있다`() {
        val seatRows = 5
        val seatColumns = 4
        val theater = Theater(seatRows, seatColumns)
        val notExistSeatPoint = Point (6, 6)
        val existSeatPoint = Point(2, 2)

        assertAll(
            { assertThat(theater.hasSeatOn(notExistSeatPoint)).isFalse },
            { assertThat(theater.hasSeatOn(existSeatPoint)).isTrue }
        )
    }
}
