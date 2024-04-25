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
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket

@ExtendWith(MockKExtension::class)
class DetailPresenterTest {
    @MockK
    private lateinit var view: DetailContract.View
    private lateinit var presenter: DetailContract.Presenter

    @BeforeEach
    fun setUp() {
//        view = mockk<DetailContract.View>()
        presenter = DetailPresenter(view)
    }

    @Test
    fun `영화 정보를 표시한다`() {
        every { view.showMovie(any()) } just runs
        presenter.loadMovie()
        verify { view.showMovie(any()) }
    }

    @Test
    fun `예약 인원이 1일 때 플러스 버튼을 누르면 예약 인원이 2가 된다`() {
        every { view.updateCount(any()) } just runs
        presenter.onClickedPlusButton(1)
        verify { view.updateCount(2) }
    }

    @Test
    fun `예약 인원이 99일 때 플러스 버튼을 눌러도 예약 인원은 변화가 없다`() {
        every { view.updateCount(any()) } just runs
        presenter.onClickedPlusButton(98)
        verify { view.updateCount(99) }
    }

    @Test
    fun `예약 인원이 2일 때 마이너스 버튼을 누르면 예약 인원이 1이 된다`() {
        every { view.updateCount(any()) } just runs
        presenter.onClickedMinusCount(2)
        verify { view.updateCount(1) }
    }

    @Test
    fun `예약 인원이 1일 때 마이너스 버튼을 눌러도 예약 인원은 변화가 없다`() {
        every { view.updateCount(any()) } just runs
        presenter.onClickedMinusCount(1)
        verify { view.updateCount(1) }
    }

    @Test
    fun `예매 완료 버튼을 누르면 예매 내역을 보여준다`() {
        every { view.showTicket(any()) } just runs
        val movie = Movie(0, "해리 포터", "", "")
        val count = 2
        presenter.onClickedReservation(movie, count)
        verify { view.showTicket(Ticket(count, movie)) }
    }
}
