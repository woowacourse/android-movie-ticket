package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieFactory
import woowacourse.movie.movielist.MovieListContract
import woowacourse.movie.movielist.MovieListPresenter

class MovieListPresenterTest {
    private lateinit var view: MovieListContract.View
    private lateinit var presenter: MovieListContract.Presenter
    private val items = MovieFactory().getAll()

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MovieListPresenter(view, items)

        every { view.showMovieList(any()) } just Runs
    }

    @Test
    fun `set 호출 시 제목과 기본 가격을 보여준다`() {
        presenter.updateMovies()

        verify {
            view.showMovieList(any())
        }
    }
}