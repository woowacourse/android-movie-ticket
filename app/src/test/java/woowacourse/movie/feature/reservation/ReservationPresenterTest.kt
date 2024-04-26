package woowacourse.movie.feature.reservation

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.data.ReservationRepository
import woowacourse.movie.feature.main.MainPresenterTest.Companion.MOCK_MOVIE

class ReservationPresenterTest {
    private lateinit var presenter: ReservationPresenter
    private lateinit var view: ReservationContract.View
    private lateinit var movieRepository: MovieRepository
    private lateinit var reservationRepository: ReservationRepository

    @BeforeEach
    fun setUp() {
        view = mockk<ReservationContract.View>()
        movieRepository = mockk<MovieRepository>()
        reservationRepository = mockk<ReservationRepository>()
        presenter = ReservationPresenter(view)
    }

    @Test
    fun `영화 데이터를 가져와 화면에 보여준다`() {
        // given
        every { movieRepository.find(any()) } returns MOCK_MOVIE
        every { view.initializeMovieDetails(any()) } just runs
        every { view.setupReservationCompleteControls() } just runs
        every { view.setupTicketQuantityControls(any()) } just runs

        // when
        presenter.fetchMovieDetails(0)

        // then
        verify { view.setupTicketQuantityControls(any()) }
    }

    @Test
    fun `예약이 끝나면 예약 완료 페이지로 이동한다`() {
        // given
        every { presenter.fetchMovieDetails(0) } just runs
        every { reservationRepository.save(any(), any()) } returns 0
        every { view.navigateToCompleteScreen(any()) } just runs

        // when
        presenter.completeSelectSchedule()

        // then
        verify { view.navigateToCompleteScreen(any()) }
    }

    @Test
    fun `예약 수량을 증가시킨다 티켓 수량을 갱신한다`() {
        // given
        every { view.updateTicketQuantity(any()) } just runs

        // when
        presenter.increaseTicketQuantity()

        // then
        verify { view.updateTicketQuantity(any()) }
    }

    @Test
    fun `예약 수량을 감소시키면 티켓 수량을 갱신한다`() {
        // given
        every { view.updateTicketQuantity(any()) } just runs

        // when
        presenter.decreaseTicketQuantity()

        // then
        verify { view.updateTicketQuantity(any()) }
    }
}
