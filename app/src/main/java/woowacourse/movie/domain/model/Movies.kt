package woowacourse.movie.domain.model

import woowacourse.movie.R
import java.time.LocalDate

class Movies {
    private var _movies: MutableList<Movie> = mutableListOf()
    val movies: List<Movie>
        get() = _movies

    init {
        initMovieList()
    }

    private fun initMovieList() {
        _movies.add(
            Movie(
                movieId = 0,
                posterImageId = R.drawable.harrypotter_poster,
                title = "해리 포터와 마법사의 돌",
                screeningInfo =
                    ScreeningInfo(
                        startDate = LocalDate.of(2024, 4, 1),
                        endDate = LocalDate.of(2024, 4, 30),
                        runningTime = 148,
                    ),
                summary =
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, " +
                        "영국과 미국 합작, 판타지 영화이다. " +
                        "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. " +
                        "크리스 콜럼버스가 감독을 맡았다.",
            ),
        )
    }
}
