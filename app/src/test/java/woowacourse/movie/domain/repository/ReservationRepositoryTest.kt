package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket

class ReservationRepositoryTest {
    private lateinit var repository: ReservationRepository2

    @BeforeEach
    fun setUp() {
        repository = DummyReservation2
    }

    @Test
    fun `스크린 정보와 티켓 개수를 통해 예약 정보를 저장하고 예약 ID를 반환한다`() {
        // given
        val screen =
            Screen(
                id = 1,
                Movie(
                    id = 1,
                    title = "해리 포터와 마법사의 돌",
                    runningTime = 152,
                    description =
                        "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                            "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                date = "2024-03-01",
                price = 13_000,
            )
        val count = 1

        // when
        val result = repository.save(screen, count)
        val id = result.getOrThrow()

        // then
        assertThat(id).isEqualTo(1)

        // when
        val reservation = repository.findById(id).getOrThrow()
        val actual =
            Reservation(
                id = 1,
                screen,
                Ticket(count),
            )

        // then
        assertThat(reservation).isEqualTo(actual)
    }
}
