package woowacourse.movie.presentation.screening

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
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
    fun `presenter 가 생성될 때, 저장소에서 상영중인 영화들을 가져온다`() {
        // given
        every { repository.screenMovies() } returns listOf()
        // when & then
        verify { repository.screenMovies() }
    }

    @Test
    fun `presenter 가 생성될 때, view 가 영화 목록을 보여준다`() {
        // given
        every { view.showMovies(any()) } just Runs
        // when & then
        verify { view.showMovies(any()) }
    }

    @Test
    fun `id 를 통해 상영 영화를 불러오는 것에 성공하면, 예약 화면으로 이동한다`() {
        // given
        val screenId = 1L
        every { repository.screenMovieById(any()) } returns Result.success(stubScreenMovie())
        // when
        presenter.startReservation(screenId)
        // then
        verify { view.navigateToReservationView(screenId) }
        verify(exactly = 0) { view.showErrorView() }
    }

    @Test
    fun `id 를 통해 상영 영화를 불러오는 것에 실패하면, 에러 화면으로 이동한다`() {
        // given
        val screenId = 1L
        every { repository.screenMovieById(any()) } returns Result.failure(IllegalArgumentException())
        // when
        presenter.startReservation(screenId)
        // then
        verify(exactly = 0) { view.navigateToReservationView(screenId) }
        verify { view.showErrorView() }
    }
}
