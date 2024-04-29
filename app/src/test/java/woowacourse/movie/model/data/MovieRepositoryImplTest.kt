package woowacourse.movie.model.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.model.equalMovie
import woowacourse.movie.model.movie1
import woowacourse.movie.model.movie2
import woowacourse.movie.model.movie3

class MovieRepositoryImplTest {
    @BeforeEach
    fun setUp() {
        MovieRepositoryImpl.deleteAll()
    }

    @Test
    fun `영화를 저장한다`() {
        MovieRepositoryImpl.save(movie1)
        val actual = MovieRepositoryImpl.find(movie1.id)
        equalMovie(actual, movie1)
    }

    @Test
    fun `이미 존재하는 id의 영화를 저장하려는 경우 예외가 발생한다`() {
        MovieRepositoryImpl.save(movie1)

        assertThrows<IllegalStateException> {
            MovieRepositoryImpl.save(movie2.copy(id = movie1.id))
        }
    }

    @Test
    fun `여러 영화를 저장한다`() {
        // given
        val movies = listOf(movie1, movie2, movie3)

        // when
        MovieRepositoryImpl.saveAll(movies)
        val actual = MovieRepositoryImpl.findAll()

        // then
        assertThat(actual.size).isEqualTo(3)
        equalMovie(actual[0], movies[0])
        equalMovie(actual[1], movies[1])
        equalMovie(actual[2], movies[2])
    }

    @Test
    fun `특정 id의 영화를 가져온다`() {
        // given
        MovieRepositoryImpl.save(movie1)
        MovieRepositoryImpl.save(movie2)
        MovieRepositoryImpl.save(movie3)

        // when
        val actual = MovieRepositoryImpl.find(movie3.id)

        // then
        equalMovie(actual, movie3)
    }

    @Test
    fun `유효하지 않은 id의 영화를 찾으려는 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            MovieRepositoryImpl.find(-1L)
        }
    }

    @Test
    fun `모든 영화를 가져온다`() {
        // given
        MovieRepositoryImpl.save(movie1)
        MovieRepositoryImpl.save(movie2)
        MovieRepositoryImpl.save(movie3)

        // when
        val actual = MovieRepositoryImpl.findAll()

        // then
        assertThat(actual.size).isEqualTo(3)
        equalMovie(actual[0], movie1)
        equalMovie(actual[1], movie2)
        equalMovie(actual[2], movie3)
    }

    @Test
    fun `모든 영화를 삭제한다`() {
        // given
        MovieRepositoryImpl.save(movie1)
        MovieRepositoryImpl.save(movie2)
        MovieRepositoryImpl.save(movie3)

        // when
        MovieRepositoryImpl.deleteAll()
        val actual = MovieRepositoryImpl.findAll()

        // then
        assertThat(actual).isEmpty()
    }
}
