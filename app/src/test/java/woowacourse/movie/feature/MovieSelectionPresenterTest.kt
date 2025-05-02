package woowacourse.movie.feature

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieSelection.MovieSelectionContract
import woowacourse.movie.feature.movieSelection.MovieSelectionPresenter
import woowacourse.movie.fixtures.MOVIE

class MovieSelectionPresenterTest {
    private lateinit var presenter: MovieSelectionPresenter
    private lateinit var view: MovieSelectionContract.View

    @BeforeEach
    fun setup() {
        view = mockk()
        presenter = MovieSelectionPresenter(view)
    }

    @Test
    fun `initializeMovies 호출 시 영화 목록을 표시한다`() {
        // given
        every { presenter.initializeMovies() } just Runs

        // when
        presenter.initializeMovies()

        // then
        verify { view.showMovies(any()) }
    }

    @Test
    fun `selectMovie 호출 시 영화 예매 화면으로 이동한다`() {
        // given
        every { presenter.selectMovie(MOVIE) } just Runs

        // when
        presenter.selectMovie(MOVIE)

        // then
        verify { view.goToReservation(any()) }
    }
}
