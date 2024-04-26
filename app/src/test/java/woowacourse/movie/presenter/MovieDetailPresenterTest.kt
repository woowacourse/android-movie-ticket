package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.contract.MovieDetailContract
import woowacourse.movie.contract.PurchaseConfirmationContract
import woowacourse.movie.repository.MovieRepository

@ExtendWith(MockKExtension::class)
class MovieDetailPresenterTest {
    @RelaxedMockK
    lateinit var view: MovieDetailContract.View

    lateinit var presenter: MovieDetailPresenter

    @RelaxedMockK
    lateinit var movieRepository: MovieRepository

    @BeforeEach
    fun setUp() {
        presenter = MovieDetailPresenter(view, movieRepository)
    }

    @Test
    fun `상영 정보를 표시할 수 있어야 한다`() {
        presenter.loadMovie(0)
        verify {
            view.displayMovie(any())
        }
    }

    @Test
    fun `티켓 개수가 1일때 plus 버튼을 누르면 티켓 개수가 2가 되어야 한다`() {
        every { view.displayTicketNum(any()) } just runs
        presenter = MovieDetailPresenter(
            view = view,
            movieRepository = movieRepository,
            ticketNum = 1
        )
        presenter.plusTicketNum()
        verify { view.displayTicketNum(2) }
    }

    @Test
    fun `티켓 개수가 2일때 minus 버튼을 누르면 티켓 개수가 1이 되어야 한다`() {
        every { view.displayTicketNum(any()) } just runs
        presenter = MovieDetailPresenter(
            view = view,
            movieRepository = movieRepository,
            ticketNum = 2
        )
        presenter.minusTicketNum()
        verify { view.displayTicketNum(1) }
    }

    @Test
    fun `확인 버튼을 누르면 결제 확인 화면으로 넘어간다`() {
        every { view.displayMovie(any()) } just runs
        presenter.purchase(0)
        verify { view.navigateToPurchaseConfirmation() }
    }

    @Test
    fun `상영일의 목록이 보여져야 한다`() {
        presenter.loadScreeningDays()
        verify {view.displayScreeningDays()}
    }
}
