package woowacourse.movie.presentation.reservation.detail

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.movie.ScreeningPeriod
import woowacourse.movie.presentation.fixture.dummyMovie
import woowacourse.movie.presentation.model.toUiModel
import woowacourse.movie.presentation.view.reservation.detail.ReservationDetailContract
import woowacourse.movie.presentation.view.reservation.detail.ReservationDetailPresenter
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationDetailPresenterTest {
    private lateinit var presenter: ReservationDetailContract.Presenter
    private lateinit var view: ReservationDetailContract.View
    private val movie =
        dummyMovie
            .copy(
                screeningPeriod =
                    ScreeningPeriod(
                        LocalDate.now(),
                        LocalDate.now().plusDays(1),
                    ),
            ).toUiModel()

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationDetailPresenter(view)
    }

    @Test
    fun `영화 데이터를 불러온다`() {
        every { view.setScreen(any()) } just Runs
        every { view.updateDates(any(), any()) } just Runs
        every { view.updateReservationCount(any(), any()) } just Runs

        presenter.fetchData { movie }

        verify { view.setScreen(movie) }
    }

    @Test
    fun `예약 수를 업데이트하면 뷰에 반영된다`() {
        every { view.updateReservationCount(any(), any()) } just Runs

        presenter.updateReservationCount(3)

        verify { view.updateReservationCount(4, any()) }
    }

    @Test
    fun `날짜를 선택하면 해당 날짜의 시간 목록을 보여준다`() {
        val now = LocalDateTime.of(2025, 4, 1, 0, 0)
        val times = dummyMovie.screeningPeriod.getAvailableTimesFor(now, now.toLocalDate())

        every { view.updateTimes(times) } just Runs
        every { view.setScreen(movie) } just Runs
        every { view.updateDates(any(), any()) } just Runs
        every { view.updateReservationCount(any(), any()) } just Runs

        presenter.fetchData { movie }
        presenter.onSelectDate(now.toLocalDate())

        verifySequence {
            view.setScreen(movie)
            view.updateDates(any(), any(), any())
            view.updateReservationCount(any(), any())
            view.updateTimes(times)
        }
    }

    @Test
    fun `예약 시 조건이 맞으면 예약 정보를 넘긴다`() {
        val now = LocalDateTime.of(2025, 4, 1, 12, 0)
        every { view.setScreen(any()) } just Runs
        every { view.updateDates(any(), any()) } just Runs
        every { view.updateReservationCount(any(), any()) } just Runs
        every { view.notifyReservationConfirm(any()) } just Runs

        presenter.fetchData(3) { movie }
        presenter.onReserve(now)

        verify {
            view.notifyReservationConfirm(
                withArg {
                    assert(it.title == movie.title)
                    assert(it.reservationDateTime == now)
                    assert(it.reservationCount == 3)
                },
            )
        }
    }

    @Test
    fun `예매 가능한 날짜가 없는 경우 다이얼로그를 보여준다`() {
        val now = LocalDate.now().minusDays(1)
        val dummyPeriod = ScreeningPeriod(now, now)
        val dummyItem =
            dummyMovie.copy(
                screeningPeriod = dummyPeriod,
            )
        every { view.notifyNoAvailableDates() } just Runs

        presenter.fetchData { dummyItem.toUiModel() }

        verify { view.notifyNoAvailableDates() }
    }

    @Test
    fun `영화 정보를 불러오지 못하는 경우 다이얼로그를 보여준다`() {
        every { view.notifyNoAvailableDates() } just Runs

        presenter.fetchData { null }

        verify { view.notifyNoAvailableDates() }
    }
}
