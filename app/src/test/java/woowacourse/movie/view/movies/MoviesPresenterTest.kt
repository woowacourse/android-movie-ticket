package woowacourse.movie.view.movies

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MoviesPresenterTest {
    private lateinit var presenter: MoviesPresenter
    private lateinit var view: MoviesContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MoviesPresenter(view)
    }

    @Test
    fun `영화 리스트를 보여준다`() {
        // given
        every { view.showMovies(any()) } just Runs
        // when
        presenter.loadData()
        // then
        verify { view.showMovies(any()) }
    }
}
