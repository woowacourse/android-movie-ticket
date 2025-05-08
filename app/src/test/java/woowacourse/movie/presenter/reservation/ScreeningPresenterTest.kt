package woowacourse.movie.presenter.reservation

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.reservation.ScreeningContract
import woowacourse.movie.data.reservation.FakeScreeningData
import woowacourse.movie.domain.reservation.Movie
import woowacourse.movie.domain.reservation.Screening
import java.time.LocalDate

class ScreeningPresenterTest {
    private lateinit var presenter: ScreeningContract.Presenter
    private lateinit var view: ScreeningContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter =
            ScreeningPresenter(
                view,
                FakeScreeningData(
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
                    ),
                ),
            )
    }

    @Test
    fun `영화 목록을 보여줄 수 있다`() {
        // given
        every {
            view.setScreeningContents(
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
                ),
            )
        } just Runs

        // when
        presenter.fetchScreeningContents()

        // then
        verify {
            view.setScreeningContents(
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
                ),
            )
        }
    }

    @Test
    fun `예매할 영화를 선택할 수 있다`() {
        // given
        every {
            view.navigateToReservationScreen(
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
        } just Runs

        // when
        presenter.selectScreening(
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

        // then
        verify {
            view.navigateToReservationScreen(
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
    }
}
