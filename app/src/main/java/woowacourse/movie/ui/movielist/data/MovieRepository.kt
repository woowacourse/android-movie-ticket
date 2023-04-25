package woowacourse.movie.ui.movielist.data

import com.woowacourse.movie.domain.Movie
import woowacourse.movie.model.MovieItem
import woowacourse.movie.model.mapper.toMovieUI
import java.time.LocalDate

object MovieRepository {
    fun allMovies(): List<MovieItem.MovieUI> = movies.map { it.toMovieUI() }
    private val movies: List<Movie> = listOf(
        Movie(
            1,
            "해리 포터와 마법사의 돌",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
        ),
        Movie(
            2,
            "범죄 도시1",
            LocalDate.of(2023, 4, 12),
            LocalDate.of(2023, 5, 14),
            121,
            "통쾌하고! 화끈하고! 살벌하게! 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!",
        ),
        Movie(
            1,
            "해리 포터와 마법사의 돌",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
        ),
        Movie(
            2,
            "범죄 도시1",
            LocalDate.of(2023, 4, 12),
            LocalDate.of(2023, 5, 14),
            121,
            "통쾌하고! 화끈하고! 살벌하게! 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!",
        ),
        Movie(
            1,
            "해리 포터와 마법사의 돌",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
        ),
        Movie(
            2,
            "범죄 도시1",
            LocalDate.of(2023, 4, 12),
            LocalDate.of(2023, 5, 14),
            121,
            "통쾌하고! 화끈하고! 살벌하게! 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!",
        ),
    )
}
