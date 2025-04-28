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
    private val fakeMovie =
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
        // Given: View가 화면 설정, 예약 수 업데이트, 날짜 업데이트를 수행하도록 설정한다.
        every { view.showScreen(any()) } just Runs
        every { view.updateReservationCount(any(), any()) } just Runs
        every { view.updateDates(any(), any(), any()) } just Runs

        // When: presenter가 영화 정보를 전달받아 fetchData를 호출하면
        presenter.fetchData(fakeMovie)

        // Then: View의 setScreen이 영화 정보를 받아 호출된다.
        verify { view.showScreen(fakeMovie) }
    }

    @Test
    fun `예약 수를 업데이트하면 뷰에 반영된다`() {
        // Given: View가 예약 수 업데이트를 수행하도록 설정한다.
        every { view.updateReservationCount(any(), any()) } just Runs

        // When: 예약 인원 수를 3명 추가 요청하면
        presenter.updateReservationCount(3)

        // Then: View가 기존 인원(1) + 3명을 반영하여 4명으로 업데이트한다.
        verify { view.updateReservationCount(4, any()) }
    }

    @Test
    fun `날짜를 선택하면 해당 날짜의 시간 목록을 보여준다`() {
        // Given: 특정 날짜에 해당하는 상영 시간을 준비하고 View의 동작을 설정한다.
        val now = LocalDateTime.of(2025, 4, 1, 0, 0)
        val times = dummyMovie.screeningPeriod.getAvailableTimesFor(now, now.toLocalDate())

        every { view.updateTimes(times) } just Runs
        every { view.showScreen(fakeMovie) } just Runs
        every { view.updateDates(any(), any(), any()) } just Runs
        every { view.updateReservationCount(any(), any()) } just Runs

        presenter.fetchData(fakeMovie)

        // When: 특정 날짜를 선택하면
        presenter.onSelectDate(now.toLocalDate())

        // Then: 화면이 초기화되고, 날짜 선택에 따라 시간 목록이 갱신된다.
        verifySequence {
            view.showScreen(fakeMovie)
            view.updateReservationCount(any(), any())
            view.updateDates(any(), any(), any())
            view.updateTimes(times)
        }
    }

    @Test
    fun `예약 시 조건이 맞으면 예약 정보를 넘긴다`() {
        // Given: View가 예약 정보를 넘길 수 있도록 설정하고, 기본 데이터를 준비한다.
        val now = LocalDateTime.of(2025, 4, 1, 12, 0)
        every { view.showScreen(any()) } just Runs
        every { view.updateDates(any(), any(), any()) } just Runs
        every { view.updateReservationCount(any(), any()) } just Runs
        every { view.notifyReservationConfirm(any(), any()) } just Runs

        presenter.fetchData(fakeMovie, 3)

        // When: 사용자가 특정 시간에 예약을 요청하면
        presenter.onReserve(now)

        // Then: View는 예약 정보를 확인하는 이벤트를 전달받는다.
        verify {
            view.notifyReservationConfirm(
                withArg {
                    assert(it.title == fakeMovie.title)
                    assert(it.reservationDateTime == now)
                    assert(it.reservationCount == 3)
                },
                any(),
            )
        }
    }

    @Test
    fun `예매 가능한 날짜가 없는 경우 다이얼로그를 보여준다`() {
        // Given: 상영 기간이 존재하지 않는 영화 데이터를 준비하고 View 설정을 한다.
        val fakeMovie =
            dummyMovie.copy(
                screeningPeriod = ScreeningPeriod(LocalDate.MIN, LocalDate.MIN),
            )
        every { view.showScreen(any()) } just Runs
        every { view.updateReservationCount(any(), any()) } just Runs
        every { view.notifyNoAvailableDates() } just Runs

        // When: 영화 데이터로 fetchData를 호출하면
        presenter.fetchData(fakeMovie.toUiModel())

        // Then: 예매 가능한 날짜가 없다는 다이얼로그를 보여준다.
        verify { view.notifyNoAvailableDates() }
    }

    @Test
    fun `영화 정보를 불러오지 못하는 경우 다이얼로그를 보여준다`() {
        // Given: View가 예매 불가 알림을 수행하도록 설정한다.
        every { view.notifyNoAvailableDates() } just Runs

        // When: null 데이터를 fetchData로 전달하면
        presenter.fetchData(null)

        // Then: 예매 가능한 영화 정보가 없다는 다이얼로그를 보여준다.
        verify { view.notifyNoAvailableDates() }
    }
}
