package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen2

class DummyScreens2 : ScreenRepository2 {
    // TODO 더미 데이터
    private val temp =
        listOf(
            Screen2(
                id = 1,
                Movie(
                    id = 1,
                    "해리 포터와 마법사의 돌",
                    151,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                "2024-03-01",
                13_000,
            ),
            Screen2(
                id = 2,
                Movie(
                    id = 2,
                    "해리 포터와 마법사의 돌2",
                    152,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                "2024-03-02",
                13_002,
            ),
            Screen2(
                id = 3,
                Movie(
                    id = 3,
                    "해리 포터와 마법사의 돌3",
                    153,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                "2024-03-03",
                13_003,
            ),
        )

    override fun load(): List<Screen2> = temp

    override fun findById(id: Int): Result<Screen2> = runCatching { temp.find { it.id == id } ?: throw NoSuchElementException() }
}
