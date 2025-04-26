package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Fixture
import woowacourse.movie.view.reservation.reservation.ReservationContract
import woowacourse.movie.view.reservation.reservation.ReservationPresenter
import java.time.LocalDate

class ReservationPresenterTest {
    private lateinit var presenter: ReservationContract.Presenter
    private lateinit var view: ReservationContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationPresenter(view)
    }

    @Test
    fun `데이터를 가져오면 영화 정보를 설정한다`() {
        // given
        every { view.updateMovieInfo(any(), any(), any(), any(), any()) } just Runs

        // when: 영화 목록을 조회하면
        presenter.fetchData { Fixture.dummyMovie }

        // then: 영화 정보를 설정한다
        verify { view.updateMovieInfo(any(), any(), any(), any(), any()) }
    }

    @Test
    fun `영화 정보를 불러오지 못하는 경우 다이얼로그를 보여준다`() {
        every { view.showErrorDialog() } just Runs

        presenter.fetchData { null }

        verify { view.showErrorDialog() }
    }

    @Test
    fun `날짜 스피너에서 날짜를 선택하면 해당 날짜의 시간 목록을 보여준다`() {
        val now = LocalDate.of(2025, 4, 25)
        every { view.updateMovieInfo(any(), any(), any(), any(), any()) } just Runs
        every { view.updateTimeAdapter(any()) } just Runs

        presenter.fetchData { Fixture.dummyMovie }
        presenter.onDateSelected(now, 0)

        verifySequence {
            view.updateMovieInfo(any(), any(), any(), any(), any())
            view.updateTimeAdapter(any())
        }
    }

    @Test
    fun `티켓 개수를 증가시키면 뷰에 반영된다`() {
        every { view.setTicketCount(any()) } just Runs

        presenter.plusTicketCount()

        verify { view.setTicketCount(any()) }
    }

    @Test
    fun `티켓 개수를 감소시키면 뷰에 반영된다`() {
        every { view.setTicketCount(any()) } just Runs

        presenter.plusTicketCount()
        presenter.minusTicketCount()

        verify { view.setTicketCount(any()) }
    }
}
