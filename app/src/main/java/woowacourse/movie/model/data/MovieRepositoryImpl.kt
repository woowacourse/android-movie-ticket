package woowacourse.movie.model.data

import woowacourse.movie.R
import woowacourse.movie.model.data.dto.Movie
import java.time.LocalDate

object MovieRepositoryImpl : MovieRepository {
    private var id: Long = 0
    private val movies = mutableMapOf<Long, Movie>()
    private const val NOT_EXIST_ID_MESSAGE = "해당하는 아이디의 영화를 찾을 수 없습니다."

    init {
        save(
            Movie(
                posterImageId = R.drawable.img_movie_poster,
                title = "해리 포터와 마법사의 돌",
                startScreeningDate = LocalDate.of(2024, 3, 1),
                endScreeningDate = LocalDate.of(2024, 3, 20),
                runningTime = 152,
                synopsis = "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, " +
                    "판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다. ",
            ),
        )
        saveAll(movieDummy)
    }

    override fun save(movie: Movie): Long {
        movies[id] = movie.copy(id = id)
        return id++
    }

    override fun saveAll(movies: List<Movie>) {
        movies.forEach { save(it) }
    }

    override fun find(id: Long): Movie {
        return movies[id] ?: throw IllegalArgumentException(NOT_EXIST_ID_MESSAGE)
    }

    override fun findAll(): List<Movie> {
        return movies.map { it.value }
    }

    override fun deleteAll() {
        movies.clear()
    }
}
