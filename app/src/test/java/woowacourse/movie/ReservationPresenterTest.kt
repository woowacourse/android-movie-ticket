package woowacourse.movie

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.presenter.screening.ReservationPresenter
import java.time.LocalDate

class ReservationPresenterTest {
    private lateinit var presenter: ReservationContract.Presenter
    private lateinit var view: ReservationContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter =
            ReservationPresenter(
                view,
                Screening(
                    Movie(
                        0,
                        "해리 포터와 마법사의 돌",
                        152,
                    ),
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ),
            )
    }

    @Test
    fun `포스터를 표시한다`() {
        // given
        every { view.setPoster(0) } just Runs

        // when
        presenter.presentPoster()

        // then
        verify { view.setPoster(0) }
    }

    @Test
    fun `제목을 표시한다`() {
        // given
        every { view.setTitle("해리 포터와 마법사의 돌") } just Runs

        // when
        presenter.presentTitle()

        // then
        verify { view.setTitle("해리 포터와 마법사의 돌") }
    }

    @Test
    fun `상영일을 표시한다`() {
        // given
        every { view.setPeriod(2025, 4, 1, 2025, 4, 25) } just Runs

        // when
        presenter.presentPeriod()

        // then
        verify { view.setPeriod(2025, 4, 1, 2025, 4, 25) }
    }

    @Test
    fun `러닝타임을 표시한다`() {
        // given
        every { view.setRunningTime(152) } just Runs

        // when
        presenter.presentRunningTime()

        // then
        verify { view.setRunningTime(152) }
    }
}
