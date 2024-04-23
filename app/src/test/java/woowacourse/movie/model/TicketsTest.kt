package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import java.time.LocalDate

class TicketsTest {
    @Test
    fun `주어진_티켓_수에_맞는_총_금액을_계산하여_반환한다`() {
        val tickets =
            Tickets(
                Count(3),
                Movie(
                    id = 0,
                    title = "해리 포터와 마법사의 돌",
                    thumbnailResourceId = R.drawable.movie1,
                    startDate = LocalDate.of(2024, 3, 1),
                    endDate = LocalDate.of(2024, 3, 31),
                    runningTime = 152,
                    introduction =
                        """
                        《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                        """.trimIndent(),
                ),
            )
        assertThat(tickets.totalPrice).isEqualTo(39000)
    }
}
