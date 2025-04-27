package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.ui.view.movies.MovieContract

class MoviesPresenterTest {
    private lateinit var view: MovieContract.View
    private lateinit var presenter: MoviePresenter
    private lateinit var movieRepository: MovieRepository
    private lateinit var movies: List<Movie>

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        movieRepository = mockk()
        presenter = MoviePresenter(view, movieRepository)
        movies = mockk()
    }

    @Test
    fun `loadAllMovies가 호출되며 모든 영화정보가 나타난다`() {
        // given
        every { movieRepository.getAllMovies() } returns movies
        every { view.showAllMovies(movies) } just runs

        // when
        presenter.loadAllMovies()

        // then
        verify {
            movieRepository.getAllMovies()
            view.showAllMovies(movies)
        }
    }
}
