package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Movie.Companion.movies
import woowacourse.movie.feature.mapper.toUi
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.movies.contract.MoviesContract
import woowacourse.movie.feature.movies.presenter.MoviesPresenter

class MoviesPresenterTest {
    private lateinit var presenter: MoviesPresenter
    private lateinit var view: MoviesContract.View

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MoviesPresenter(view)
    }

    @Test
    fun `prepareMovies 호출 시 영화 목록을 보여준다`() {
        // given & when
        val movies = slot<List<MovieUiModel>>()
        presenter.prepareMovies()

        // then
        verify {
            view.showMovies(capture(movies))
        }

        assertThat(movies.captured).isEqualTo(Movie.movies.map { it.toUi() })
    }

    @Test
    fun `selectMovieForBooking 호출 시 예약 상세 화면으로 이동한다`() {
        // given
        val movie = slot<MovieUiModel>()
        val movieUiModel = movies.first().toUi()

        // when
        presenter.selectMovieForBooking(movieUiModel)

        // then
        verify {
            view.navigateToBookingDetail(capture(movie))
        }

        assertThat(movie.captured).isEqualTo(movieUiModel)
    }
}
