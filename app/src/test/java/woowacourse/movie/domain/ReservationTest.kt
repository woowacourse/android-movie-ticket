package woowacourse.movie.domain

import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.LocalDate

@RunWith(Parameterized::class)
class ReservationTest(val peopleCount: Int) {

    @Test
    fun `예매 인원이 1명 이상 200명 이하가 아니면 에러가 발생한다`() {
        assertThrows(
            "[ERROR] 예매 인원은 최소 1명 이상 최대 200명 이하여야 합니다.",
            IllegalArgumentException::class.java
        ) {
            Reservation(getAnyMovie(), peopleCount)
        }
    }

    private fun getAnyMovie(): Movie =
        Movie("아바타", LocalDate.now(), Minute(120), Poster(1), MovieDetail("줄거리"))

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun peopleCount(): Collection<Int> {
            return listOf(0, 201)
        }
    }
}
