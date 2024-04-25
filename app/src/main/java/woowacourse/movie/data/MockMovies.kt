package woowacourse.movie.data

import woowacourse.movie.domain.model.Movie
import java.time.LocalDate

object MockMovies {
    private const val DEFAULT_MOVIE_TITLE = "영화가 존재하지 않습니다."

    val sampleMovies =
        listOf(
            Movie(
                movieId = 1,
                title = "해리 포터와 마법사의 돌",
                screeningStartDate = LocalDate.of(2024, 3, 1),
                screeningEndDate = LocalDate.of(2024, 3, 31),
                runningTime = 152,
                imageName = "harry_potter_poster_1",
                description =
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. " +
                        "크리스 콜럼버스가 감독을 맡았다.",
            ),
            Movie(
                movieId = 2,
                title = "해리 포터와 비밀의 방",
                screeningStartDate = LocalDate.of(2024, 4, 1),
                screeningEndDate = LocalDate.of(2024, 4, 28),
                runningTime = 162,
                imageName = "harry_potter_poster_2",
                description = "해리 포터와 비밀의 방",
            ),
        )

    val defaultMovie =
        Movie(
            movieId = -1,
            title = DEFAULT_MOVIE_TITLE,
            screeningStartDate =  LocalDate.now(),
            screeningEndDate =  LocalDate.now(),
            runningTime = 0,
            imageName = null,
            description = "",
        )
}
