package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MockMovies
import woowacourse.movie.domain.DateMaker
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.reservation.MovieReservationContract
import woowacourse.movie.presentation.reservation.MovieReservationPresenter
import java.time.LocalDate
import java.time.LocalTime


class MovieReservationPresenterTest {
    private lateinit var view: MovieReservationContract.View
    private lateinit var movieRepository: MovieRepository
    private lateinit var dateMaker: DateMaker
    private lateinit var presenter: MovieReservationPresenter
    private val movieId = 1

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        movieRepository = mockk(relaxed = true)
        dateMaker = mockk(relaxed = true)
        presenter = MovieReservationPresenter(view, movieId, movieRepository, dateMaker)
    }

    @Test
    fun `영화 정보를 불러와야 한다`() {
        val movie = MockMovies.defaultMovie
        every { movieRepository.getMovie(movieId) } returns movie

        presenter.loadMovie()

        verify { view.showMovie(movie) }
    }

    @Test
    fun `날짜 범위에 따른 날짜 정보를 불러와야 한다`() {
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(10)
        val dates = listOf(startDate, endDate)
        every { dateMaker.getDatesBetween(startDate, endDate) } returns dates

        presenter.loadDate(startDate, endDate)

        verify { view.showDate(dates) }
    }

    @Test
    fun `선택된 날짜에 따라 시간 정보를 불러와야 한다`() {
        val currentDate = LocalDate.now()
        val times = listOf(LocalTime.NOON)
        every { dateMaker.getDateTimes(currentDate) } returns times

        presenter.loadTime(currentDate)

        verify { view.showTime(times) }
    }

    @Test
    fun `티켓 수를 감소시켜야 한다`() {
        presenter.decreaseTicketCount()

        verify { view.showCurrentResultTicketCountView() }
    }

    @Test
    fun `티켓 수를 증가시켜야 한다`() {
        presenter.increaseTicketCount()

        verify { view.showCurrentResultTicketCountView() }
    }

    @Test
    fun `선택한 날짜를 설정해야 한다`() {
        val date = LocalDate.now()

        presenter.selectDate(date)
    }

    @Test
    fun `선택한 시간을 설정해야 한다`() {
        val time = LocalTime.NOON

        presenter.selectTime(time)
    }

    @Test
    fun `예약을 진행하면 좌석 선택 화면으로 이동해야 한다`() {
        val count = 3
        val title = "영화 제목"

        presenter.reservation(title, count)

        verify { view.moveToSeatSelection(any()) }
    }
}
