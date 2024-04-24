package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.presenter.detail.ReservationDetailContract
import woowacourse.movie.presenter.detail.ReservationDetailPresenter

@ExtendWith(MockKExtension::class)
class ReservationDetailPresenterTest {
    @MockK
    private lateinit var view: ReservationDetailContract.View
    private lateinit var presenter: ReservationDetailContract.Presenter

    @BeforeEach
    fun setUp() {
        presenter = ReservationDetailPresenter(view, ScreeningDao())
    }

    @Test
    fun `영화 정보를 보여준다`() {
        every { view.showMovieInformation(any()) } just runs
        presenter.loadMovie(0)
        verify { view.showMovieInformation(any()) }
    }

    @Test
    fun `예약 인원이 1인 상태에서 마이너스 버튼을 누르면 토스트를 보여준다`() {
        every { view.showResultToast() } just runs
        presenter.decreaseTicketCount(1)
        verify { view.showResultToast() }
    }

    @Test
    fun `예약 인원이 2인 상태에 마이너스 버튼을 누르면 예약 인원은 1이 된다`() {
        every { view.changeHeadCount(any()) } just runs
        presenter.decreaseTicketCount(2)
        verify { view.changeHeadCount(1) }
    }

    @Test
    fun `예약 인원이 100인 상태에서 플러스 버튼을 누르면 토스트를 보여준다`() {
        every { view.showResultToast() } just runs
        presenter.increaseTicketCount(100)
        verify { view.showResultToast() }
    }

    @Test
    fun `예약 인원이 2인 상태에서 플러스 버튼을 누르면 예약 인원은 3이 된다`() {
        every { view.changeHeadCount(any()) } just runs
        presenter.increaseTicketCount(2)
        verify { view.changeHeadCount(3) }
    }
}
