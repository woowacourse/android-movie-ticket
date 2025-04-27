package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.presenter.movieSelection.MovieSelectionContract
import woowacourse.movie.presenter.movieSelection.MovieSelectionPresenter
import woowacourse.movie.view.model.toUiModel
import java.time.LocalDate

class MovieSelectionPresenterTest {
    private lateinit var presenter: MovieSelectionPresenter
    private lateinit var view: MovieSelectionContract.View

    @BeforeEach
    fun setup() {
        view = mockk()
        presenter = MovieSelectionPresenter(view)
    }

    @Test
    fun `영화 선택 시 영화 예매 화면으로 이동한다`() {
        // given
        val movie =
            Movie(
                "해리 포터와 마법사의 돌",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 5, 31),
                runningTime = 152,
            ).toUiModel()
        every { view.goToReservation(movie) } just Runs

        // when
        presenter.onMovieSelection(movie)

        // then
        verify(exactly = 1) { view.goToReservation(movie) }
    }
}
