package woowacourse.movie.domain.repository

import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.SeatBoard

class DummyScreens : ScreenRepository {
    // TODO 더미 데이터
    private val temp =
        listOf(
            Screen(
                id = 1,
                movie =
                    Movie(
                        title = "해리 포터와 마법사의 돌",
                        runningTime = 152,
                        imageSrc = R.drawable.img_poster,
                        description =
                            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                                "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                    ),
                startDate = "2024-03-01",
                endDate = "2024-03-07",
                selectableDates =
                    listOf(
                        "2024-03-01",
                        "2024-03-02",
                        "2024-03-03",
                        "2024-03-04",
                        "2024-03-05",
                        "2024-03-06",
                        "2024-03-07",
                    ),
                selectableTimes =
                    listOf(
                        "09:00",
                        "11:00",
                        "13:00",
                        "15:00",
                        "17:00",
                        "19:00",
                        "21:00",
                        "23:00",
                    ),
                13_000,
            ),
        )

    override fun load(): List<Screen> = temp

    override fun loadSeatBoard(id: Int): Result<SeatBoard> =
        runCatching {
            DummySeatBoard.seatBoards.find { it.id == id } ?: throw NoSuchElementException()
        }

    override fun findByScreenId(id: Int): Result<Screen> = runCatching { temp.find { it.id == id } ?: throw NoSuchElementException() }
}
