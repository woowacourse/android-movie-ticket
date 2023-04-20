package woowacourse.movie.repository

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Minute
import woowacourse.movie.domain.Movie

class MovieRepositoryTest {

    @Test
    fun `영화의 아이디가 1보다 작은 영화를 저장하면 에러가 발생한다`() {
        val movie = Movie(
            -1,
            "title",
            Minute(1),
            "summary"
        )

        assertThatIllegalArgumentException().isThrownBy {
            MovieRepository.save(movie)
        }.withMessage("순서에 맞는 아이디의 영화만 저장할 수 있습니다.")
    }

    @Test
    fun `영화의 아이디가 다음 아이디보다 큰 영화를 저장하면 에러가 발생한다`() {
        val nextId = MovieRepository.nextId
        val movie = Movie(
            nextId + 1,
            "title",
            Minute(1),
            "summary"
        )

        assertThatIllegalArgumentException().isThrownBy {
            MovieRepository.save(movie)
        }.withMessage("순서에 맞는 아이디의 영화만 저장할 수 있습니다.")
    }

    @Test
    fun `이미 저장되지 않은 영화를 저장하면 영화가 저장된 후 다음 아이디가 1 올라간다`() {
        val initId = MovieRepository.nextId
        val movie = Movie(
            initId,
            "title",
            Minute(1),
            "summary"
        )
        MovieRepository.save(movie)

        val actual = MovieRepository.nextId

        val expected = initId + 1
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `순서에 맞는 아이디를 가진 영화를 저장하면 저장된다`() {
        val initId = MovieRepository.nextId
        val movie = Movie(
            initId,
            "title",
            Minute(1),
            "summary"
        )
        MovieRepository.save(movie)

        val actual = MovieRepository.findById(movie.id)

        assertThat(actual).isEqualTo(movie)
    }

    @Test
    fun `이미 저장된 영화를 다시 저장하면 대체된다`() {
        val movie = Movie(
            MovieRepository.nextId,
            "title",
            Minute(1),
            "summary"
        )
        MovieRepository.save(movie)
        val otherMovie = Movie(
            movie.id,
            "otherMovieTitle",
            Minute(1),
            "summary"
        )
        MovieRepository.save(otherMovie)

        val actual = MovieRepository.findById(movie.id)

        assertThat(actual).isEqualTo(otherMovie)
    }

    @Test
    fun `저장되지 않는 영화의 아이디로 영화를 조회하면 에러가 발생한다`() {
        val notExistMovieId = 100L

        assertThatIllegalArgumentException().isThrownBy {
            MovieRepository.findById(notExistMovieId)
        }.withMessage("아이디가 ${notExistMovieId}인 영화가 존재하지 않습니다.")
    }
}
