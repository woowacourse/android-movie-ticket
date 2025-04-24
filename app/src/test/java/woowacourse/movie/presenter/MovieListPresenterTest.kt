package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.view.movies.MovieListContract
import woowacourse.movie.view.movies.MovieListPresenter

class MovieListPresenterTest {
    private lateinit var view: MovieListContract.View
    private lateinit var model: MovieListContract.MovieModel
    private lateinit var presenter: MovieListPresenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieListContract.View>(relaxed = true)
        model = mockk<MovieListContract.MovieModel>()
        presenter = MovieListPresenter(view, model)
    }

    @Test
    fun `Presenter는 setMovies 호출 시 View에 영화 목록을 전달해야 한다`() {
        // given
        presenter.setMovies()

        // then
        verify(exactly = 1) { view.showMovieList(model) }
    }

    @Test
    fun `화면 전환시 선택된 영화의 인덱스를 전달한다`() {
        presenter.onSelectMovie(1)

        verify(exactly = 1) { view.moveToBookingComplete(1) }
    }
}
