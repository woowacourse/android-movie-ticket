package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.contract.MovieListContract

@ExtendWith(MockKExtension::class)
class MovieListPresenterTest {
    @RelaxedMockK
    lateinit var view: MovieListContract.View

    @InjectMockKs
    lateinit var presenter: MovieListPresenter

    @Test
    fun `상영중인 영화의 리스트를 표기할 수 있어야 한다`() {
        presenter.loadMovies()
        verify { view.displayMovies(any()) }
    }

    @Test
    fun `지금 예매 버튼을 누르면 상영 상세 화면으로 넘어가야 한다`() {
        every { presenter.loadMovies() } just runs
        val screeningId = 0
        presenter.selectMovie(screeningId)
        verify { view.navigateToMovieDetail(screeningId) }
    }
}
