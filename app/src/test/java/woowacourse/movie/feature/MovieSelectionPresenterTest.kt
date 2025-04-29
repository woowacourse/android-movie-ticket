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
import woowacourse.movie.fixtures.movie

class MovieSelectionPresenterTest {
    private lateinit var presenter: MovieSelectionPresenter
    private lateinit var view: MovieSelectionContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter = MovieSelectionPresenter(view)
    }

    @Test
    fun `실행 시 영화 목록을 표시한다`() {
        // when
        presenter.onViewCreated()

        // then
        verify { view.showMovies(any()) }
    }

    @Test
    fun `영화 선택 시 영화 예매 화면으로 이동한다`() {
        // given
        every { view.goToReservation(any()) } just Runs

        // when
        presenter.onMovieSelection(movie)

        // then
        verify { view.goToReservation(any()) }
    }
}
