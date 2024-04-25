package woowacourse.movie.reservation.detail

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Ticket

class ReservationDetailPresenterTest {

    private lateinit var view: ReservationDetailContract.View
    private lateinit var presenter: ReservationDetailContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<ReservationDetailContract.View>()
        presenter = ReservationDetailPresenter(view, 0, Ticket(10))
    }

    @Test
    fun `예매할 영화 정보를 표시한다`() {
        every { view.showMovieInformation(any()) } just runs

        presenter.loadMovie()

        verify { view.showMovieInformation(any()) }
    }

    @Test
    fun `인원이 10일 때 Count를 늘리면 인원이 1 증가한다`() {
        every { view.updateCount(any()) } just runs

        presenter.increaseCount()

        verify { view.updateCount(11) }
    }

    @Test
    fun `인원이 10일 때 Count를 줄이면 인원이 1 감소한다`() {
        every { view.updateCount(any()) } just runs

        presenter.decreaseCount()

        verify { view.updateCount(9) }
    }

    @Test
    fun `인원이 1일 때 Count를 줄이면 에러 토스트가 나온다`() {
        every { view.showErrorToast() } just runs

        presenter = ReservationDetailPresenter(view, 0, Ticket(1))

        presenter.decreaseCount()

        verify { view.showErrorToast() }
    }

    @Test
    fun `인원이 100일 때 Count를 늘리면 에러 토스트가 나온다`() {
        every { view.showErrorToast() } just runs

        presenter = ReservationDetailPresenter(view, 0, Ticket(100))

        presenter.increaseCount()

        verify { view.showErrorToast() }
    }
}