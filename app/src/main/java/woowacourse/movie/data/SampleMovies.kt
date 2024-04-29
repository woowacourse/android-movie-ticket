package woowacourse.movie.data

import woowacourse.movie.domain.model.Movie
import java.time.LocalDate

object SampleMovies {
    val movies =
        listOf(
            Movie(
                movieId = 1,
                posterName = "harrypotter_poster",
                title = "해리 포터와 마법사의 돌",
                screeningDates =
                listOf(
                    LocalDate.of(2024, 4, 1),
                    LocalDate.of(2024, 4, 2),
                    LocalDate.of(2024, 4, 20),
                ),
                runningTime = 152,
                summary =
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. " +
                        "크리스 콜럼버스가 감독을 맡았다.",
            ),
            
            Movie(
                movieId = 2,
                posterName = "harrypotter_poster2",
                title = "해리 포터와 비밀의 방",
                screeningDates =
                listOf(
                    LocalDate.of(2024, 4, 10),
                    LocalDate.of(2024, 4, 20),
                ),
                runningTime = 161,
                summary =
                "《해리 포터와 비밀의 방》은 2002년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 두 번째에 해당하는 작품이다. " +
                        "크리스 콜럼버스가 감독을 맡았다.",
            ),
            
            Movie(
                movieId = 3,
                posterName = "harrypotter_poster3",
                title = "해리 포터와 아즈카반의 죄수",
                screeningDates =
                listOf(
                    LocalDate.of(2024, 4, 1),
                    LocalDate.of(2024, 4, 2),
                    LocalDate.of(2024, 4, 20),
                ),
                runningTime = 142,
                summary =
                "《해리 포터와 아즈카반의 죄수》은 2004년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 세 번째에 해당하는 작품이다. " +
                        "알폰소 쿠아론이 감독을 맡았다.",
            ),
            
            Movie(
                movieId = 4,
                posterName = "harrypotter_poster4",
                title = "해리 포터와 불의 잔",
                screeningDates =
                listOf(
                    LocalDate.of(2024, 4, 2),
                    LocalDate.of(2024, 4, 3),
                    LocalDate.of(2024, 4, 4),
                    LocalDate.of(2024, 4, 20),
                ),
                runningTime = 157,
                summary =
                "《해리 포터와 불의 잔》은 2005년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 네 번째에 해당하는 작품이다. " +
                        "마이크 뉴웰이 감독을 맡았다.",
            ),
            Movie(
                movieId = 5,
                posterName = "harrypotter_poster",
                title = "해리 포터와 냠냠",
                screeningDates =
                listOf(
                    LocalDate.of(2024, 4, 2),
                    LocalDate.of(2024, 4, 3),
                    LocalDate.of(2024, 4, 4),
                    LocalDate.of(2024, 4, 20),
                ),
                runningTime = 157,
                summary =
                "《해리 포터와 불의 잔》은 2005년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 네 번째에 해당하는 작품이다. " +
                        "마이크 뉴웰이 감독을 맡았다.",
            ),
            Movie(
                movieId = 6,
                posterName = "harrypotter_poster",
                title = "해리 포터와 냠냠냠",
                screeningDates =
                listOf(
                    LocalDate.of(2024, 4, 2),
                    LocalDate.of(2024, 4, 3),
                    LocalDate.of(2024, 4, 4),
                    LocalDate.of(2024, 4, 20),
                ),
                runningTime = 157,
                summary =
                "《해리 포터와 불의 잔》은 2005년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 네 번째에 해당하는 작품이다. " +
                        "마이크 뉴웰이 감독을 맡았다.",
            ),
            
            Movie(
                movieId = 7,
                posterName = "harrypotter_poster",
                title = "해리 냠냠과 냠냠냠",
                screeningDates =
                listOf(
                    LocalDate.of(2024, 4, 2),
                    LocalDate.of(2024, 4, 3),
                    LocalDate.of(2024, 4, 4),
                    LocalDate.of(2024, 4, 20),
                ),
                runningTime = 157,
                summary =
                "《해리 포터와 불의 잔》은 2005년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 네 번째에 해당하는 작품이다. " +
                        "마이크 뉴웰이 감독을 맡았다.",
            ),
            
            )
}
