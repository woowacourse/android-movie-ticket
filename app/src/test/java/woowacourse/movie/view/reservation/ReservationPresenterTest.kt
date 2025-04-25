package woowacourse.movie.view.reservation

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.ScreeningPeriod
import woowacourse.movie.view.movies.MoviesPresenter
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationPresenterTest {
    private lateinit var presenter: ReservationPresenter
    private lateinit var view: ReservationContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationPresenter(view)
    }

    @Test
    fun `영화 정보를 불러온다`() {
        // given
        every { view.updateReservationCount(any()) } just Runs
        every { view.showMovieDetail(any()) } just Runs
        // when
        presenter.loadData(MoviesPresenter.dummyMovie)
        // then
        verify { view.showMovieDetail(MoviesPresenter.dummyMovie) }
    }

    @Test
    fun `예약 인원이 증가한다`() {
        // given
        every { view.updateReservationCount(any()) } just Runs
        // when
        presenter.increaseCount(2)
        // then
        verify { view.updateReservationCount(3) }
    }

    @Test
    fun `예약 인원이 감소한다`() {
        // given
        every { view.showMovieDetail(any()) } just Runs
        every { view.updateReservationCount(any()) } just Runs
        // when
        presenter.loadData(MoviesPresenter.dummyMovie, 3)
        presenter.decreaseCount(1)
        // then
        verify { view.updateReservationCount(2) }
    }

    @Test
    fun `날짜를 선택하면 해당 날짜의 선택 가능한 시간 목록을 보여준다`() {
        val now = LocalDateTime.of(2025, 4, 1, 0, 0)
        val times = MoviesPresenter.dummyMovie.screeningPeriod.getAvailableTimesFor(now, now.toLocalDate())
        // given
        every { view.showMovieDetail(any()) } just Runs
        every { view.updateReservationCount(any()) } just Runs
        every { view.updateDateSet(any()) } just Runs
        every { view.updateTimeSet(any()) } just Runs

        // when
        presenter.loadData(MoviesPresenter.dummyMovie)
        presenter.selectDate(now.toLocalDate())

        // then
        verify { view.updateTimeSet(times) }
    }

    @Test
    fun `예약 시 조건이 맞으면 예약 정보를 넘긴다`() {
        val now = LocalDateTime.of(2025, 4, 1, 12, 0)
        // given
        every { view.showMovieDetail(any()) } just Runs
        every { view.updateReservationCount(any()) } just Runs
        every { view.navigateToReservationResultScreen(any()) } just Runs

        // when
        presenter.loadData(MoviesPresenter.dummyMovie, 3)
        presenter.onReserve(now.toLocalDate(), now.toLocalTime())
        // then
        verify {
            view.navigateToReservationResultScreen(
                withArg {
                    assert(it.title == MoviesPresenter.dummyMovie.title)
                    assert(it.reservationDateTime == now)
                    assert(it.reservationCount.value == 3)
                },
            )
        }
    }

    @Test
    fun `예매 가능한 날짜가 없는 경우 알려준다`() {
        val now = LocalDate.of(2025, 4, 1)
        val dummyPeriod = ScreeningPeriod(now, now)
        val dummyItem =
            MoviesPresenter.dummyMovie.copy(
                screeningPeriod = dummyPeriod,
            )
        // given
        every { view.showMovieDetail(any()) } just Runs
        every { view.updateReservationCount(any()) } just Runs
        every { view.updateDateSet(any()) } just Runs
        every { view.updateTimeSet(any()) } just Runs

        every { view.notifyUnavailableDate() } just Runs
        // when
        presenter.loadData(dummyItem)
        presenter.selectDate(LocalDate.now())
        // then
        verify { view.notifyUnavailableDate() }
    }
}
