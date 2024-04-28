package woowacourse.movie.feature.seat

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SeatSelectionPresenterTest {
    private lateinit var presenter: SeatSelectionContract.Presenter
    private lateinit var view: SeatSelectionContract.View

    @BeforeEach
    fun setUp() {
        view = mockk<SeatSelectionContract.View>()
        presenter =
            SeatSelectionPresenter(
                view,
                0,
                0,
                0,
            )
    }

    @Test
    fun `좌석 데이터를 가져와 화면에 표시한다`() {
        // given
        every { view.initialize(any(), any()) } just runs

        // when
        presenter.fetchData()

        // then
        verify { view.initialize(any(), any()) }
    }

    @Test
    fun `티켓을 저장하면 예약 완료 화면으로 넘어간다`() {
        // given
        every { view.initialize(any(), any()) } just runs
        every { view.navigateToReservationCompleted(any()) } just runs
        presenter.fetchData()

        // when
        presenter.saveTicket(listOf("A1"), 10000)

        // then
        verify { view.navigateToReservationCompleted(any()) }
    }
}
