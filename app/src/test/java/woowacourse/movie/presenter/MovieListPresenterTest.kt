package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieStore
import woowacourse.movie.fixture.advertiseUiModelFixture
import woowacourse.movie.fixture.movieUiModel1Fixture
import woowacourse.movie.fixture.movieUiModel2Fixture
import woowacourse.movie.fixture.movieUiModel3Fixture
import woowacourse.movie.view.movies.MovieListContract
import woowacourse.movie.view.movies.MovieListPresenter

class MovieListPresenterTest {
    private val view: MovieListContract.View = mockk<MovieListContract.View>(relaxed = true)

    @Test
    fun `영화 리스트를 로딩하면 영화와 광고가 포함된 리스트를 View에 전달한다`() {
        // given
        val presenter = MovieListPresenter(view, MovieStore())

        // when
        presenter.loadUiData()

        // then
        val expected =
            listOf(
                movieUiModel1Fixture,
                movieUiModel2Fixture,
                movieUiModel3Fixture,
                advertiseUiModelFixture,
            )

        verify { view.showMovieList(expected) }
    }
}
