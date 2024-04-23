package woowacourse.movie.model.data

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.movieContent

class MovieContentsImplTest {
    @BeforeEach
    fun setUp() {
        MovieContentsImpl.deleteAll()
    }

    @Test
    fun `영화_정보를_저장한다`() {
        // given

        // when
        val id = MovieContentsImpl.save(movieContent)
        val actual = MovieContentsImpl.find(id)

        // then
        assertThat(actual).isEqualTo(movieContent.copy(id = id))
    }

    @Test
    fun `특정 id의 영화 정보를 가져온다`() {
        // given
        MovieContentsImpl.save(movieContent.copy(title = "1"))
        MovieContentsImpl.save(movieContent.copy(title = "2"))
        val id = MovieContentsImpl.save(movieContent.copy(title = "3"))

        // when
        val actual = MovieContentsImpl.find(id)

        // then
        assertThat(actual.title).isEqualTo("3")
    }

    @Test
    fun `유효하지 않은 id인 경우 예외가 발생한다`() {
        assertThatThrownBy { MovieContentsImpl.find(-1) }
            .isExactlyInstanceOf(NoSuchElementException::class.java)
            .hasMessage("Movie not found with id: -1")
    }

    @Test
    fun `모든 영화 정보를 가져온다`() {
        // given
        MovieContentsImpl.save(movieContent.copy(title = "1"))
        MovieContentsImpl.save(movieContent.copy(title = "2"))
        MovieContentsImpl.save(movieContent.copy(title = "3"))

        // when
        val actual = MovieContentsImpl.findAll()

        // then
        assertThat(actual.size).isEqualTo(3)
        assertThat(actual[0].title).isEqualTo("1")
        assertThat(actual[1].title).isEqualTo("2")
        assertThat(actual[2].title).isEqualTo("3")
    }

    @Test
    fun `모든 영화 정보를 삭제한다`() {
        // given
        MovieContentsImpl.save(movieContent.copy(title = "1")) // id : 0
        MovieContentsImpl.save(movieContent.copy(title = "2")) // id : 1
        MovieContentsImpl.save(movieContent.copy(title = "3")) // id : 2

        // when
        MovieContentsImpl.deleteAll()
        val actual = MovieContentsImpl.findAll()

        // then
        assertThat(actual).isEmpty()
    }
}
