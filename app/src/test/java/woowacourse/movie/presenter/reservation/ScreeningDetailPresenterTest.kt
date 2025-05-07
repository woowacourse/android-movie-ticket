package woowacourse.movie.presenter.reservation

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.reservation.ScreeningDetailContract
import woowacourse.movie.domain.reservation.Movie
import woowacourse.movie.domain.reservation.Screening
import java.time.LocalDate
import java.time.LocalTime

class ScreeningDetailPresenterTest {
    private lateinit var presenter: ScreeningDetailContract.Presenter
    private lateinit var view: ScreeningDetailContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter =
            ScreeningDetailPresenter(
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
    fun `상영에 대한 정보들을 표시한다`() {
        // given
        every {
            view.setScreeningDetail(
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
            view.setTicketCount(1)
        } just Runs

        // when
        presenter.fetchScreeningDetail()

        // then
    }

    @Test
    fun `선택 가능한 시간들을 표시한다`() {
        // given
        every {
            view.setAvailableTimes(
                listOf(
                    LocalTime.of(10, 0),
                    LocalTime.of(12, 0),
                    LocalTime.of(14, 0),
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    LocalTime.of(22, 0),
                ),
                0,
            )
        } just Runs

        // when
        presenter.fetchAvailableTimes(LocalDate.of(2024, 4, 24))

        // then
        verify {
            view.setAvailableTimes(
                listOf(
                    LocalTime.of(10, 0),
                    LocalTime.of(12, 0),
                    LocalTime.of(14, 0),
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    LocalTime.of(22, 0),
                ),
                0,
            )
        }
    }

    @Test
    fun `티켓 수를 늘릴 수 있다`() {
        // given
        every { view.setTicketCount(2) } just Runs

        // when
        presenter.plusTicketCount()

        // then
        verify { view.setTicketCount(2) }
    }

    @Test
    fun `티켓 수를 줄일 수 있다`() {
        // given
        presenter =
            ScreeningDetailPresenter(
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
                ticketCount = 3,
            )
        every { view.setTicketCount(2) } just Runs

        // when
        presenter.minusTicketCount()

        // then
        verify { view.setTicketCount(2) }
    }
}
