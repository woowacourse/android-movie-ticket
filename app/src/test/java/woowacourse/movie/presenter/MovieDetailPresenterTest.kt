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
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate

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
        every { view.displayScreeningDates(any()) } just runs
        val startDate = ScreeningDate(LocalDate.of(1111,1,1))
        val endDate = ScreeningDate(LocalDate.of(2222,2,2))
        val period = ScreeningPeriod(startDate, endDate)
        presenter.loadScreeningPeriod(period)
        verify { view.displayScreeningDates(period) }
    }

    @Test
    fun `상영일 선택 후 상영시간의 목록이 보여져야 한다`() {
        val startDate = ScreeningDate(LocalDate.of(1111,1,1))
        val endDate = ScreeningDate(LocalDate.of(2222,2,2))
        val period = ScreeningPeriod(startDate, endDate)
        presenter.loadScreeningPeriod(period)
        presenter.selectScreeningDate(0)
        verify { view.displayScreeningTimes(startDate) }
    }
}
