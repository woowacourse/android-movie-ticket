package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieStore
import woowacourse.movie.domain.fixture.moviesFixture
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.movies.MovieListContract
import woowacourse.movie.view.movies.MovieListPresenter
import woowacourse.movie.view.movies.model.UiModel

class MovieListPresenterTest {
    private lateinit var view: MovieListContract.View
    private lateinit var model: List<Movie>

    @BeforeEach
    fun setUp() {
        view = mockk<MovieListContract.View>(relaxed = true)
        model = MovieStore().getAll()
    }

    @Test
    fun `영화 리스트를 로딩하면 영화와 광고가 포함된 리스트를 View에 전달한다`() {
        val movies = moviesFixture

        val presenter = MovieListPresenter(view, MovieStore())

        presenter.loadUiData()

        verify {
            view.showMovieList(
                match { uiModels ->
                    uiModels[0] is UiModel.MovieUiModel &&
                        uiModels[1] is UiModel.MovieUiModel &&
                        uiModels[2] is UiModel.MovieUiModel &&
                        uiModels[3] is UiModel.AdvertiseUiModel
                },
            )
        }
    }
}
