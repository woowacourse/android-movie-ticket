package woowacourse.movie.presentation.reservation.result

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.presentation.reservation.booking.stubMovieReservation
import woowacourse.movie.repository.MovieRepository

@ExtendWith(MockKExtension::class)
class ReservationResultPresenterTest {
    @RelaxedMockK
    private lateinit var view: ReservationResultView

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    @InjectMockKs
    private lateinit var presenter: ReservationResultPresenter

    @Test
    fun `예매한 영화 정보 가져오는 것을 성공하면, 결과 화면을 보여준다`() {
        // given
        val id = 1L
        val idSlot = slot<Long>()
        val reservationSlot = slot<ReservationResultUiModel>()
        every { repository.movieReservationById(capture(idSlot)) } returns stubMovieReservation()
        every { view.showResult(capture(reservationSlot)) } just Runs
        // when
        presenter.loadReservationResult(id)
        // then
        verify { repository.movieReservationById(idSlot.captured) }
        verify { view.showResult(reservationSlot.captured) }
        verify(exactly = 0) { view.showErrorView() }
    }

    @Test
    fun `예매한 영화 정보 가져오는 것을 실패하면, 에러 화면을 보여준다`() {
        // given
        val id = 1L
        val idSlot = slot<Long>()
        every { repository.movieReservationById(capture(idSlot)) } returns null
        // when
        presenter.loadReservationResult(id)
        // then
        verify { repository.movieReservationById(idSlot.captured) }
        verify { view.showErrorView() }
        verify(exactly = 0) { view.showResult(any()) }
    }
}