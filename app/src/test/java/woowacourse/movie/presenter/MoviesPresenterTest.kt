package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.Movies
import woowacourse.movie.presenter.MoviesPresenter
import woowacourse.movie.fixture.MovieFixture

class MoviesPresenterTest {
    private lateinit var view: Movies.View
    private lateinit var presenter: Movies.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MoviesPresenter(view)
    }

    @Test
    fun loadMovies_호출_시_View의_showMovies가_호출된다() {
        // given - 영화 목록이 주어짐
        every { view.showMovies(any()) } just Runs
        // when - loadMovies를 호출 시
        presenter.loadMovies()
        // then - showMovies가 호출된다
        verify { view.showMovies(any()) }
    }

    @Test
    fun `selectedMovie 호출 시 View의 navigateToBook이 호출된다`() {
        val movie = MovieFixture.movie
        // given - 영화 목록이 보여지고 있음
        every { view.navigateToBook(any()) } just Runs
        // when - 영화 하나를 선택시
        presenter.selectedMovie(movie)
        // then - 영화 예매 페이지를 호출한다
        verify { view.navigateToBook(any()) }
    }
}
