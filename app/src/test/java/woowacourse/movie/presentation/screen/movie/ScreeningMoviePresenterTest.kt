package woowacourse.movie.presentation.screen.movie

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.fixtures.movie

@ExtendWith(MockKExtension::class)
class ScreeningMoviePresenterTest {
    @MockK
    private lateinit var view: ScreeningMovieContract.View

    @InjectMockKs
    private lateinit var presenter: ScreeningMoviePresenter

    @Test
    fun `영화가 들어오면, id 값으로 다음 화면으로 이동한다`() {
        // given
        val movie = movie(id = 1)
        every { view.startNextActivity(movie.id) } just Runs
        // when
        presenter.registerMovie(movie)
        // then
        verify { view.startNextActivity(movie.id) }
    }
}
