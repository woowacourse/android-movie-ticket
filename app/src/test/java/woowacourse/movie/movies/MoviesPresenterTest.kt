package woowacourse.movie.movies

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movies.MovieRepository
import woowacourse.movie.ui.movies.MovieContract
import woowacourse.movie.ui.movies.MoviePresenter

class MoviesPresenterTest {
    private lateinit var view: MovieContract.View
    private lateinit var presenter: MoviePresenter
    private lateinit var movieRepository: MovieRepository

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        movieRepository = mockk()
        presenter = MoviePresenter(view, movieRepository)
    }

    @Test
    fun `loadAllMovies가 호출되며 모든 영화정보가 나타난다`() {
        // given
        every { movieRepository.getMovieListItems() } returns mockk()
        every { view.showAllMovies(any()) } just runs

        // when
        presenter.loadAllMovies()

        // then
        verify {
            movieRepository.getMovieListItems()
            view.showAllMovies(any())
        }
    }
}
