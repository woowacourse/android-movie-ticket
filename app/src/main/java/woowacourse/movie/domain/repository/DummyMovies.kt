package woowacourse.movie.domain.repository

import woowacourse.movie.R
import woowacourse.movie.domain.model.DrawableImage
import woowacourse.movie.domain.model.Image
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MoviePoster

class DummyMovies : MovieRepository {
    private val movies =
        listOf(
            Movie(
                1,
                "해리 포터와 마법사의 돌 ",
                151,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                    "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            ),
            Movie(
                2,
                "해리 포터와 마법사의 돌2",
                152,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                    "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            ),
            Movie(
                3,
                "해리 포터와 마법사의 돌3",
                153,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                    "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            ),
        )

    private val movieImages =
        listOf(
            MoviePoster(1, DrawableImage(R.drawable.img_poster)),
            MoviePoster(2, DrawableImage(R.drawable.ic_launcher_foreground)),
            MoviePoster(3, DrawableImage(R.drawable.ic_launcher_background)),
        )

    override fun findById(id: Int): Movie = movies.find { it.id == id } ?: throw NoSuchElementException()

    override fun imageSrc(id: Int): Image<Any> = movieImages.find { it.movieId == id }?.poster ?: throw NoSuchElementException()
}
