package woowacourse.movie

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.ScreeningContract
import woowacourse.movie.data.screening.FakeScreenings
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.presenter.screening.ScreeningPresenter
import java.time.LocalDate

class ScreeningPresenterTest {
    private lateinit var presenter: ScreeningContract.Presenter
    private lateinit var view: ScreeningContract.View
    private val fakeScreenings: List<Screening> =
        listOf(
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

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter =
            ScreeningPresenter(
                view,
                FakeScreenings(fakeScreenings),
            )
    }

    @Test
    fun `영화 목록을 보여줄 수 있다`() {
        // given
        every { view.setScreenings(fakeScreenings) } just Runs

        // when
        presenter.updateScreenings()

        // then
        verify { view.setScreenings(fakeScreenings) }
    }
}
