package woowacourse.movie

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.activity.movielist.MovieListContract
import woowacourse.movie.activity.movielist.MovieListPresenter
import woowacourse.movie.domain.Movie
import java.time.Duration
import java.time.LocalDate

class MovieListPresenterTest {
    private lateinit var presenter: MovieListPresenter
    private lateinit var view: MovieListContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MovieListPresenter(view)
    }

    @Test
    fun `loadMovies 호출 시 view의 showMovieList가 호출된다`() {
        // given
        every { view.showMovieList(any()) } just Runs

        // when
        presenter.loadMovies()

        // then
        verify(exactly = 1) { view.showMovieList(any()) }
    }

    @Test
    fun `onMovieClicked 호출 시 view의 moveToBooking이 호출된다`() {
        // given
        val movie =
            Movie(
                "해리 포터와 마법사의 돌",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
                Duration.ofMinutes(152),
            )
        every { view.moveToBooking(movie) } just Runs

        // when
        presenter.onMovieClicked(movie)

        // then
        verify(exactly = 1) { view.moveToBooking(movie) }
    }
}
