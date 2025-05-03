package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.DefaultMovieRepository
import woowacourse.movie.view.movie.MovieListContract
import woowacourse.movie.view.movie.MovieListPresenter

class MovieListPresenterTest {
    private lateinit var presenter: MovieListContract.Presenter
    private lateinit var view: MovieListContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieListPresenter(view, DefaultMovieRepository())
    }

    @Test
    fun `영화 목록이 보인다`() {
        presenter.loadMovieListScreen()
        verify { view.showMovieList(any()) }
    }
}
