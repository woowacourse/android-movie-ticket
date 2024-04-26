package woowacourse.movie.ui.detail

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.FakeImage
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.FakeMovieRepository
import woowacourse.movie.domain.repository.FakeScreenRepository
import woowacourse.movie.ui.MovieDetailUI
import woowacourse.movie.ui.ScreenDetailUI

class ScreenDetailPresenterTest {
    private val mockView = mockk<ScreenDetailContract.View>()
    private val presenter =
        ScreenDetailPresenter(
            view = mockView,
            movieRepository = FakeMovieRepository(),
            screenRepository = FakeScreenRepository(),
            reservationRepository = DummyReservation,
        )

    private val fakeScreenDetailUI =
        ScreenDetailUI(
            id = 1,
            movieDetailUI =
            MovieDetailUI(
                title = "title1",
                runningTime = 1,
                description =
                "description1",
                image = FakeImage("1"),
            ),
            date = "1",
        )

    @Test
    fun `영화 정보를 표시`() {
        // given
        every { mockView.showScreen(fakeScreenDetailUI) } just runs

        // when
        presenter.loadScreen(1)

        // then
        verify(exactly = 1) { mockView.showScreen(fakeScreenDetailUI) }
    }


}
