package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie

object MockMovies {
    private const val DEFAULT_MOVIE_TITLE = "영화가 존재하지 않습니다."

    val sampleMovies =
        listOf(
            Movie(
                movieId = 1,
                title = "해리 포터와 마법사의 돌",
                screeningDate = "2024.3.1",
                runningTime = 152,
                imageName = "harry_potter_poster",
                description =
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. " +
                        "크리스 콜럼버스가 감독을 맡았다.",
            ),
        )

    val defaultMovie =
        Movie(
            movieId = -1,
            title = DEFAULT_MOVIE_TITLE,
            screeningDate = "",
            runningTime = 0,
            imageName = null,
            description = "",
        )
}
