package woowacourse.movie.model.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.dto.nullMovie
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
    fun `영화_정보를_저장한다`() {
        val id = MovieRepositoryImpl.save(movie1)
        val actual = MovieRepositoryImpl.find(id)
        equalMovie(actual, movie1)
    }

    @Test
    fun `여러_영화_정보를_저장한다`() {
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
    fun `특정 id의 영화 정보를 가져온다`() {
        // given
        MovieRepositoryImpl.save(movie1.copy(title = "1"))
        MovieRepositoryImpl.save(movie1.copy(title = "2"))
        val id = MovieRepositoryImpl.save(movie1.copy(title = "3"))

        // when
        val actual = MovieRepositoryImpl.find(id)

        // then
        assertThat(actual.title).isEqualTo("3")
    }

    @Test
    fun `유효하지 않은 id인 경우 빈 영화 정보를 가져온다`() {
        val actual = MovieRepositoryImpl.find(-1)
        equalMovie(actual, nullMovie)
    }

    @Test
    fun `모든 영화 정보를 가져온다`() {
        // given
        MovieRepositoryImpl.save(movie1.copy(title = "1"))
        MovieRepositoryImpl.save(movie1.copy(title = "2"))
        MovieRepositoryImpl.save(movie1.copy(title = "3"))

        // when
        val actual = MovieRepositoryImpl.findAll()

        // then
        assertThat(actual.size).isEqualTo(3)
        assertThat(actual[0].title).isEqualTo("1")
        assertThat(actual[1].title).isEqualTo("2")
        assertThat(actual[2].title).isEqualTo("3")
    }

    @Test
    fun `모든 영화 정보를 삭제한다`() {
        // given
        MovieRepositoryImpl.save(movie1.copy(title = "1")) // id : 0
        MovieRepositoryImpl.save(movie1.copy(title = "2")) // id : 1
        MovieRepositoryImpl.save(movie1.copy(title = "3")) // id : 2

        // when
        MovieRepositoryImpl.deleteAll()
        val actual = MovieRepositoryImpl.findAll()

        // then
        assertThat(actual).isEmpty()
    }
}
