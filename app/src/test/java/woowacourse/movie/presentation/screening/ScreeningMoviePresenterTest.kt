package woowacourse.movie.presentation.screening

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
import woowacourse.movie.repository.MovieRepository

@ExtendWith(MockKExtension::class)
class ScreeningMoviePresenterTest {
    @RelaxedMockK
    lateinit var view: ScreeningMovieView

    @RelaxedMockK
    lateinit var repository: MovieRepository

    @InjectMockKs
    lateinit var presenter: ScreeningMoviePresenter

    @Test
    fun `presenter 가 상영중인 영화들을 가져오면, view 가 영화 목록을 보여준다`() {
        // given
        val moviesSlot = slot<List<ScreeningMovieUiModel>>()
        every { repository.screenMovies() } returns stubScreenMovies()
        every { view.showMovies(capture(moviesSlot)) } just Runs
        // when
        presenter.loadScreenMovies()
        // then
        verify { repository.screenMovies() }
        verify { view.showMovies(moviesSlot.captured) }
    }

    @Test
    fun `id 를 통해 상영 영화를 불러오는 것에 성공하면, 예약 화면으로 이동한다`() {
        // given
        val screenId = 1L
        val slot = slot<Long>()
        every { repository.screenMovieById(capture(slot)) } returns stubScreenMovie()
        // when
        presenter.startReservation(screenId)
        // then
        verify { repository.screenMovieById(slot.captured) }
        verify { view.navigateToReservationView(slot.captured) }
        verify(exactly = 0) { view.showErrorView() }
    }

    @Test
    fun `id 를 통해 상영 영화를 불러오는 것에 실패하면, 에러 화면을 보여준다`() {
        // given
        val screenId = 1L
        val slot = slot<Long>()
        every { repository.screenMovieById(capture(slot)) } returns null
        // when
        presenter.startReservation(screenId)
        // then
        verify { repository.screenMovieById(slot.captured) }
        verify(exactly = 0) { view.navigateToReservationView(any()) }
        verify { view.showErrorView() }
    }
}
