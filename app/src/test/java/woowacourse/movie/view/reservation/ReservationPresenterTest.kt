package woowacourse.movie.view.reservation

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.ScreeningPeriod
import woowacourse.movie.view.fixture.dummyMovie
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationPresenterTest {
    private lateinit var presenter: ReservationContract.Presenter
    private lateinit var view: ReservationContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationPresenter(view)
    }

    @Test
    fun `영화 데이터를 불러온다`() {
        every { view.setScreen(any()) } just Runs
        every { view.updateDateSpinner(any(), any()) } just Runs
        every { view.updateReservationCount(any()) } just Runs

        presenter.fetchData { dummyMovie }

        verify { view.setScreen(dummyMovie) }
    }

    @Test
    fun `예약 수를 업데이트하면 뷰에 반영된다`() {
        every { view.updateReservationCount(any()) } just Runs

        presenter.updateReservationCount(3)

        verify { view.updateReservationCount(4) }
    }

    @Test
    fun `날짜를 선택하면 해당 날짜의 시간 목록을 보여준다`() {
        val now = LocalDateTime.of(2025, 4, 1, 0, 0)
        val times = dummyMovie.screeningPeriod.getAvailableTimesFor(now, now.toLocalDate())

        every { view.updateTimeSpinner(times) } just Runs
        every { view.setScreen(dummyMovie) } just Runs
        every { view.updateDateSpinner(any(), any()) } just Runs
        every { view.updateReservationCount(any()) } just Runs

        presenter.fetchData { dummyMovie }
        presenter.onSelectDate(now.toLocalDate())

        verifySequence {
            view.setScreen(dummyMovie)
            view.updateDateSpinner(any(), any(), any())
            view.updateReservationCount(any())
            view.updateTimeSpinner(times)
        }
    }

    @Test
    fun `예약 시 조건이 맞으면 예약 정보를 넘긴다`() {
        val now = LocalDateTime.of(2025, 4, 1, 12, 0)
        every { view.setScreen(any()) } just Runs
        every { view.updateDateSpinner(any(), any()) } just Runs
        every { view.updateReservationCount(any()) } just Runs
        every { view.navigateToResult(any()) } just Runs

        presenter.fetchData(3) { dummyMovie }
        presenter.onReserve(now)

        verify {
            view.navigateToResult(
                withArg {
                    assert(it.title == dummyMovie.title)
                    assert(it.reservationDateTime == now)
                    assert(it.reservationCount.value == 3)
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
        every { view.showNoAvailableTimesDialog() } just Runs

        presenter.fetchData { dummyItem }

        verify { view.showNoAvailableTimesDialog() }
    }

    @Test
    fun `영화 정보를 불러오지 못하는 경우 다이얼로그를 보여준다`() {
        every { view.showNoAvailableTimesDialog() } just Runs

        presenter.fetchData { null }

        verify { view.showNoAvailableTimesDialog() }
    }
}
