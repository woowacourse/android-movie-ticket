package woowacourse.movie.presenter.reservation

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.contract.reservation.ReservationContract
import woowacourse.movie.domain.reservation.Movie
import woowacourse.movie.domain.reservation.Screening
import java.time.LocalDate
import java.time.LocalTime

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

    @Test
    fun `선택 가능한 날짜들을 표시한다`() {
        // given
        every {
            view.setDates(
                Screening(
                    Movie(
                        0,
                        "해리 포터와 마법사의 돌",
                        152,
                    ),
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ).availableDates(),
            )
        } just Runs

        // when
        presenter.presentDates()

        // then
        verify {
            view.setDates(
                Screening(
                    Movie(
                        0,
                        "해리 포터와 마법사의 돌",
                        152,
                    ),
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ).availableDates(),
            )
        }
    }

    @Test
    fun `선택 가능한 시간들을 표시한다`() {
        // given
        every {
            view.setTimes(
                listOf(
                    LocalTime.of(10, 0),
                    LocalTime.of(12, 0),
                    LocalTime.of(14, 0),
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    LocalTime.of(22, 0),
                ),
            )
        } just Runs

        // when
        presenter.presentTimes(LocalDate.of(2024, 4, 24))

        // then
        verify {
            view.setTimes(
                listOf(
                    LocalTime.of(10, 0),
                    LocalTime.of(12, 0),
                    LocalTime.of(14, 0),
                    LocalTime.of(16, 0),
                    LocalTime.of(18, 0),
                    LocalTime.of(20, 0),
                    LocalTime.of(22, 0),
                ),
            )
        }
    }

    @Test
    fun `티켓 수를 늘릴 수 있다`() {
        // given
        every { view.setTicketCount(2) } just Runs

        // when
        presenter.plusTicketCount(1)

        // then
        verify { view.setTicketCount(2) }
    }

    @Test
    fun `티켓 수를 줄일 수 있다`() {
        // given
        every { view.setTicketCount(1) } just Runs

        // when
        presenter.minusTicketCount(2)

        // then
        verify { view.setTicketCount(1) }
    }

    @Test
    fun `예매 확인 다이얼로그를 띄울 수 있다`() {
        // given
        every { view.showConfirmDialog() } just Runs

        // when
        presenter.tryReservation()

        // then
        verify { view.showConfirmDialog() }
    }

    @Test
    fun `예매를 완료할 수 있다`() {
        // given
        every { view.navigateSeatSelectionScreen("해리 포터와 마법사의 돌") } just Runs

        // when
        presenter.confirmReservation()

        // then
        verify { view.navigateSeatSelectionScreen("해리 포터와 마법사의 돌") }
    }
}
