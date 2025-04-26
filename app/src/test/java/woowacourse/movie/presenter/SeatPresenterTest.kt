package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import woowacourse.movie.domain.APRIL_THIRTIETH
import woowacourse.movie.domain.HARRY_POTTER_MOVIE
import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.seat.SeatContract
import woowacourse.movie.seat.SeatPresenter
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.test.Test

class SeatPresenterTest {
    private lateinit var view: SeatContract.View
    private lateinit var presenter: SeatPresenter
    private lateinit var reservation: Reservation

    @BeforeEach
    fun setUp() {
        view = mockk<SeatContract.View>(relaxed = true)
        presenter = SeatPresenter(view)
        reservation =
            Reservation(
                movie = HARRY_POTTER_MOVIE,
                _count = TicketCount(1),
                reservedTime =
                    LocalDateTime.of(
                        APRIL_THIRTIETH,
                        LocalTime.of(12, 0),
                    ),
            )
        presenter.initReservation(reservation)
    }

    @Test
    fun `initView는 View의 showMovieInfo, initSeat, initSelectButtonClick, updateTotalPrice를 호출한다`() {
        // when
        presenter.initView()

        // then
        verify { view.showMovieInfo(reservation.movie) }
        verify { view.initSeat() }
        verify { view.initSelectButtonClick() }
        verify { view.updateTotalPrice(any()) }
    }

    @Test
    fun `좌석을 선택하면 추가되고 총 가격을 업데이트 한다`() {
        // given
        val point = Point(0, 0)

        // when
        presenter.selectSeat(point)
        val actual = reservation.points.has(point)

        // then
        assertTrue(actual)
        verify { view.updateTotalPrice(any()) }
    }

    @Test
    fun `좌석을 다시 선택하면 제거되고 총 가격을 업데이트 한다`() {
        // given
        val point = Point(0, 0)
        reservation.points + point

        // when
        presenter.cancelSelection(point)
        val actual = reservation.points.has(point)

        // then
        assertFalse(actual)
        verify { view.updateTotalPrice(any()) }
    }

    @Test
    fun `인원 수와 선택한 좌석 수가 같으면 true를 반환한다`() {
        // given
        val point = Point(0, 0)

        // when
        reservation.points + point
        val actual = presenter.canReserve()

        // then
        assertTrue(actual)
    }

    @Test
    fun `인원 수와 선택한 좌석 수가 다르면 false를 반환한다`() {
        assertFalse(presenter.canReserve())
    }

    @Test
    fun `선택한 좌석이 하나라도 존재하면 true를 반환한다`() {
        // when
        reservation.points + Point(0, 0)
        val actual = presenter.canClickButton()

        // then
        assertTrue(actual)
    }

    @Test
    fun `선택한 좌석이 존재하지 않으면 false를 반환한다`() {
        assertFalse(presenter.canClickButton())
    }

    @Test
    fun `선택한 좌석이 이미 선택되어 있으면 true를 반환한다`() {
        // given
        val point = Point(0, 0)
        reservation.points + point

        // when
        val actual = presenter.isOccupied(point)

        // then
        assertTrue(actual)
    }

    @Test
    fun `선택한 좌석이 선택되어 있지 않으면 false를 반환한다`() {
        // given
        val point = Point(0, 0)

        // when
        val actual = presenter.isOccupied(point)

        // then
        assertFalse(actual)
    }
}
