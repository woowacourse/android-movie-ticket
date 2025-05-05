package woowacourse.movie.domain

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.view.movies.MainContract
import woowacourse.movie.view.movies.MainPresenter

class MainPresenterTest {
    private lateinit var view: MainContract.View
    private lateinit var presenter: MainContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `데이터를 가져오면 화면에 띄워진다`() {
        // given
        val capturedMovies = slot<List<MovieItem>>()
        every { view.showMoviesScreen(capture(capturedMovies)) } just Runs
        // when
        presenter.fetchData()
        // then
        verify { view.showMoviesScreen(any()) }

        val expectedMovies = convertToMovieItems(Movie.dummy)
        assertThat(capturedMovies.captured).isEqualTo(expectedMovies)
    }

    private fun convertToMovieItems(movies: List<Movie>): List<MovieItem> {
        val items = mutableListOf<MovieItem>()
        movies.forEachIndexed { index, movie ->
            items.add(MovieItem.Movie(movie))
            if ((index + 1) % 3 == 0) {
                items.add(MovieItem.Advertisement)
            }
        }
        return items
    }
}
