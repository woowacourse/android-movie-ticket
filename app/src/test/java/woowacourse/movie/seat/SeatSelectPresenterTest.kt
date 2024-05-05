package woowacourse.movie.seat

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Seats

class SeatSelectPresenterTest {
    private lateinit var view: SeatSelectContract.View
    private lateinit var presenter: SeatSelectContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<SeatSelectContract.View>()

        every { view.showReservationInfo(any(), any()) } just runs

        presenter = SeatSelectPresenter(view, 0, Seats(10), ReservationSchedule())
    }

    @Test
    fun `저장된 데이터를 불러온다`() {
        every { view.showTotalPrice(any()) } just runs

        presenter.loadSavedData()

        verify { view.showTotalPrice(any()) }
    }

    @Test
    fun `예매 확인 다이얼로그를 보여준다`() {
        every { view.showConfirmDialog() } just runs

        presenter.confirm()

        verify { view.showConfirmDialog() }
    }

    @Test
    fun `영화 제목을 불러온다`() {
        every { view.showReservationInfo(any(), any()) } just runs

        presenter.loadMovieTitle()

        verify { view.showReservationInfo(any(), any()) }
    }

    @Test
    fun `영화 예매 정보를 불러온다`() {
        every {
            view.moveToReservationFinished(
                any(), any(), any(), any(), any(),
            )
        } just runs

        presenter.loadReservationInformation()

        verify {
            view.moveToReservationFinished(
                any(),
                any(),
                any(),
                any(),
                any(),
            )
        }
    }
}
