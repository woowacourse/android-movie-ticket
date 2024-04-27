package woowacourse.movie.model

import woowacourse.movie.R
import woowacourse.movie.model.screening.DatePeriod
import woowacourse.movie.model.screening.Movie
import woowacourse.movie.model.screening.Screening
import java.time.LocalDate

object MovieData {
    private val MOVIES =
        listOf(
            Movie(
                movieId = 0,
                title = "해리 포터와 마법사의 돌",
                thumbnailResourceId = R.drawable.thumbnail_movie1,
                runningTime = 152,
                introduction =
                    """
                    《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                    """.trimIndent(),
            ),
            Movie(
                movieId = 1,
                title = "해리 포터와 비밀의 방",
                thumbnailResourceId = R.drawable.thumbnail_movie2,
                runningTime = 162,
                introduction =
                    """
                    《해리 포터와 비밀의 방》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                    """.trimIndent(),
            ),
            Movie(
                movieId = 2,
                title = "해리 포터와 아즈카반의 죄수",
                thumbnailResourceId = R.drawable.thumbnail_movie3,
                runningTime = 141,
                introduction =
                    """
                    《해리 포터와 아즈카반의 죄수》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                    """.trimIndent(),
            ),
            Movie(
                movieId = 3,
                title = "해리 포터와 불의 잔",
                thumbnailResourceId = R.drawable.thumbnail_movie4,
                runningTime = 157,
                introduction =
                    """
                    《해리 포터와 불의 잔》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                    """.trimIndent(),
            ),
        )

    val SCREENING_DATA =
        listOf(
            Screening.of(
                screeningId = 0,
                movieId = 0,
                datePeriod =
                    DatePeriod(
                        startDate = LocalDate.of(2024, 3, 1),
                        endDate = LocalDate.of(2024, 3, 31),
                        dateSpan = 1,
                    ),
            ),
            Screening.of(
                screeningId = 1,
                movieId = 1,
                datePeriod =
                    DatePeriod(
                        startDate = LocalDate.of(2024, 4, 1),
                        endDate = LocalDate.of(2024, 4, 28),
                        dateSpan = 1,
                    ),
            ),
            Screening.of(
                screeningId = 2,
                movieId = 2,
                datePeriod =
                    DatePeriod(
                        startDate = LocalDate.of(2024, 5, 1),
                        endDate = LocalDate.of(2024, 5, 31),
                        dateSpan = 1,
                    ),
            ),
            Screening.of(
                screeningId = 3,
                movieId = 3,
                datePeriod =
                    DatePeriod(
                        startDate = LocalDate.of(2024, 6, 1),
                        endDate = LocalDate.of(2024, 6, 30),
                        dateSpan = 1,
                    ),
            ),
        )

    val ADVERTISEMENT_DRAWABLE_ID = R.drawable.screening_advertisement

    fun findMovieById(id: Long): Result<Movie> {
        val movie = MOVIES.find { it.movieId == id }
        return movie?.let { Result.Success(it) } ?: Result.Error("존재하지 않는 아이디 값입니다.")
    }

    fun findScreeningDataById(screeningId: Long): Result<Screening> {
        val screening = SCREENING_DATA.find { it.screeningId == screeningId }
        return screening?.let { Result.Success(it) } ?: Result.Error("존재하지 않는 상영 정보입니다.")
    }
}
