package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.Seats
import woowacourse.movie.view.seat.SeatContract
import woowacourse.movie.view.seat.SeatPresenter
import woowacourse.movie.view.seat.model.coord.Column
import woowacourse.movie.view.seat.model.coord.Coordination
import woowacourse.movie.view.seat.model.coord.Row

class SeatPresenterTest {
    private lateinit var view: SeatContract.View
    private lateinit var model: Seats
    private lateinit var presenter: SeatPresenter

    @BeforeEach
    fun setUp() {
        view = mockk<SeatContract.View>()
        model = mockk()
        presenter = SeatPresenter(view, Seats())
    }

    @Test
    fun `선택된_좌석이_선택된적이_없다면_해당_좌석을_반환한다`() {
        // when
        val pos = Coordination(Column(1), Row(1))
        val seat = Seat(1, 1)

        // given
        every { view.showSeat(any()) } just Runs
        every { model.isSelected(seat) } returns false
        every { model.item } returns setOf(seat)

        presenter.changeSeat(pos)

        // then
        verify { model.addSeat(seat) }
        verify { view.showSeat(listOf(pos)) }
    }
}
