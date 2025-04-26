package woowacourse.movie.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.fixture.MovieFixture

class MoviesTest {
    @Test
    fun 영화들의_key와_value의_title이_일치해야한다() {
        val movie = MovieFixture.movies2
        assertThrows<IllegalArgumentException> {
            Movies(movie)
        }
    }

    @Test
    fun 영화_목록_중_한_영화를_찾을_수_있다() {
        val movies = Movies(MovieFixture.movies)
        val title = Title("해리포터와 마법사의 돌")

        val actual = movies.find(title)

        val expected = MovieFixture.movie

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 영화_목록_중_없는_영화는_찾을_수_없다() {
        val movies = Movies(MovieFixture.movies)
        val title = Title("해리포터와 마법사")

        assertThrows<IllegalArgumentException> { movies.find(title) }
    }

    @Test
    fun 영화_목록을_list형식으로_반환할_수_있다() {
        val movies = Movies(MovieFixture.movies)

        val actual = movies.toList()

        val expected = MovieFixture.listMovies

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun 영화_목록의_개수가_10000개이면_추가가_되지_않는다() {
        val maxMovies: Movies = MovieFixture.MAX_MOVIES

        assertThrows<java.lang.IllegalArgumentException> {
            maxMovies.add(MovieFixture.movie)
        }
    }
}
