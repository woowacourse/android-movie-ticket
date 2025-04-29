package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
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
        val reservationInfoSlot = slot<ReservationInfo>()

        // given
        every { view.showReservationInfo(capture(reservationInfoSlot)) } just Runs

        // when
        presenter.fetchData { dummyReservationInfo }

        // then
        verify { view.showReservationInfo(any()) }

        assertThat(reservationInfoSlot.captured.title).isEqualTo("라라랜드")
        assertThat(reservationInfoSlot.captured.time).isEqualTo("14:00")
        assertThat(reservationInfoSlot.captured.price).isEqualTo(20000)
    }
}
