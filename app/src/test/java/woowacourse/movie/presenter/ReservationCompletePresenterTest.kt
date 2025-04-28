package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.dummyReservationInfo
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.view.reservation.complete.ReservationCompleteContract
import woowacourse.movie.view.reservation.complete.ReservationCompletePresenter

class ReservationCompletePresenterTest {
    private lateinit var presenter: ReservationCompleteContract.Presenter
    private lateinit var view: ReservationCompleteContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationCompletePresenter(view)
    }

    @Test
    fun `데이터를 가져오면 예매 정보를 화면에 표시한다`() {
        // given
        val slot1 = slot<ReservationInfo>()
        every { view.updateReservationInfo(capture(slot1)) } just Runs

        // when
        presenter.fetchData { dummyReservationInfo }

        // then
        verify { view.updateReservationInfo(any()) }
    }
}
