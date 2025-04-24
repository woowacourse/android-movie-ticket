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
    fun `제목을 표시한다`() {
        // given
        every { view.setTitle("해리 포터와 마법사의 돌") } just Runs

        // when
        presenter.presentTitle()

        // then
        verify { view.setTitle("해리 포터와 마법사의 돌") }
    }
}
