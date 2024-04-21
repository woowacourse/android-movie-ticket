package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.DrawableImage
import woowacourse.movie.domain.model.Movie

class MovieRepositoryTest {
    private val movieRepository = DummyMovies()

    @Test
    fun `영화 id 로 영화 포스터를 찾는다`() {
        val actual = movieRepository.imageSrc(1)

        // TODO: R.drawable 은 안드로이드 의존성 때문에 테스트 불가
        assertThat(actual).isExactlyInstanceOf(DrawableImage::class.java)
    }

    @Test
    fun `영화 id 로 영화를 찾는다`() {
        val actual = movieRepository.findById(1)
        val expected =
            Movie(
                1,
                "해리 포터와 마법사의 돌 ",
                151,
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                    "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            )

        assertThat(actual).isEqualTo(expected)
    }
}
