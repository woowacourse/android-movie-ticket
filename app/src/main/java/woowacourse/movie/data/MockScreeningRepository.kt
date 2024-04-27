package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.screening.Screening
import java.time.LocalDate

object MockScreeningRepository : ScreeningRepository {
    private val screenings: MutableList<Screening> =
        mutableListOf(
            Screening(
                0,
                Movie(
                    R.drawable.sorcerer_ston_poster,
                    "해리 포터와 마법사의 돌",
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, " +
                        "영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다." +
                        "크리스 콜럼버스가 감독을 맡았다.",
                    152,
                ),
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 4, 30),
            ),
            Screening(
                1,
                Movie(
                    R.drawable.secret_room_poster,
                    "해리 포터와 비밀의 방",
                    "《해리 포터와 비밀의 방》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다.",
                    162,
                ),
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 28),
            ),
            Screening(
                2,
                Movie(
                    R.drawable.azkaban_prisoner_poster,
                    "해리 포터와 아즈카반의 죄수",
                    "《해리 포터와 아즈카반의 죄수》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, " +
                        "영국과 미국 합작, 판타지 영화이다.",
                    141,
                ),
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 31),
            ),
            Screening(
                3,
                Movie(
                    R.drawable.goblet_of_fire_poster,
                    "해리 포터와 불의 잔",
                    "《해리 포터와 불의 잔》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다.",
                    157,
                ),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 30),
            ),
        )

    override fun findAll(): List<Screening> = screenings

    override fun find(id: Long): Screening? = screenings.find { it.id == id }
}
