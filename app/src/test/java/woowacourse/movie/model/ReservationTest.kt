package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket

class ReservationTest {
    @Test
    fun `예매 티켓이 세 장이고, 영화 가격이 13,000 일 때 총 가격은 39,000 이다`() {
        // given & when
        val screen =
            Screen(
                id = 1,
                Movie(
                    "해리 포터와 마법사의 돌",
                    152,
                    0,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                "2024-03-01",
                13_000,
            )
        val reservation =
            Reservation(
                id = 1,
                screen,
                Ticket(3),
            )

        // then
        assertThat(reservation.totalPrice).isEqualTo(39_000)
    }
}
