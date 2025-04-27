package woowacourse.movie.presenter

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import woowacourse.movie.RESERVATION
import woowacourse.movie.reserve.ReserveContract
import woowacourse.movie.reserve.ReservePresenter
import java.time.LocalDateTime
import kotlin.test.Test

class ReservePresenterTest {
    private lateinit var view: ReserveContract.View
    private lateinit var presenter: ReservePresenter

    @BeforeEach
    fun setUp() {
        view = mockk<ReserveContract.View>(relaxed = true)
        presenter = ReservePresenter(view)
        presenter.reservation = RESERVATION
    }

    @Test
    fun `onPlusButtonClick 호출 시 티켓 수가 증가한다`() {
        // given
        val beforeCount = presenter.reservation.count

        // when
        presenter.increaseTicketCount()
        val actual = presenter.reservation.count

        // then
        assertEquals(beforeCount + 1, actual)
    }

    @Test
    fun `onMinusButtonClick 호출 시 티켓 수가 감소한다`() {
        // given
        presenter.increaseTicketCount()
        val beforeCount = presenter.reservation.count

        // when
        presenter.decreaseTicketCount()
        val actual = presenter.reservation.count

        // then
        assertEquals(beforeCount - 1, actual)
    }

    @Test
    fun `updateReservedTime 호출 시 reservation이 업데이트된다`() {
        // given
        val newDateTime = LocalDateTime.now().plusDays(1)

        // when
        presenter.updateReservedTime(newDateTime)
        val actual = presenter.reservation.reservedTime

        // then
        assertEquals(newDateTime, actual)
    }
}
