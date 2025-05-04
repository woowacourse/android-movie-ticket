package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.ui.movielist.contract.MovieListContract
import woowacourse.movie.ui.movielist.presenter.MovieListPresenter

class MovieListPresenterTest {
    private lateinit var presenter: MovieListPresenter
    private lateinit var view: MovieListContract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MovieListPresenter(view)
    }

    @Test
    fun `프레젠터의 loadMovieList가 호출되면 뷰의 setMoveListItems이 호출된다`() {
        presenter.loadMovieListItems()
        verify { view.setMoveListItems(any()) }
    }
}
