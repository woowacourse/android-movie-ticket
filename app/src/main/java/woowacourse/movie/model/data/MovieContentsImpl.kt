package woowacourse.movie.model.data

import woowacourse.movie.R
import woowacourse.movie.model.data.dto.Date
import woowacourse.movie.model.data.dto.MovieContent
import woowacourse.movie.model.data.dto.nullMovieContent

object MovieContentsImpl : MovieContents {
    private var id: Long = 0
    private val movieContents = mutableMapOf<Long, MovieContent>()

    init {
        save(
            MovieContent(
                R.drawable.movie_poster,
                "해리 포터와 마법사의 돌",
                Date(2024, 3, 1),
                152,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, " +

                    "판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다. ",
            ),
        )
    }

    override fun save(movieContent: MovieContent): Long {
        movieContents[id] = movieContent.copy(id = id)
        return id++
    }

    override fun find(id: Long): MovieContent {
        return movieContents[id] ?: nullMovieContent
    }

    override fun findAll(): List<MovieContent> {
        return movieContents.map { it.value }
    }

    override fun deleteAll() {
        movieContents.clear()
    }
}
