package woowacourse.movie

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Result

val MOVIES =
    listOf(
        Movie(
            id = 0,
            title = "해리 포터와 마법사의 돌",
            thumbnail = R.drawable.movie1,
            date = "2024.3.1",
            runningTime = 152,
            introduction =
                """
                《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                """.trimIndent(),
        ),
    )

fun findMovieById(id: Int): Result<Movie> {
    val movie = MOVIES.find { it.id == id }
    return movie?.let { Result.Success(it) } ?: Result.Error("존재하지 않는 아이디 값입니다.")
}
