package woowacourse.movie.presentation.movies

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.presentation.view.movies.MoviesContract
import woowacourse.movie.presentation.view.movies.MoviesPresenter

class MoviesPresenterTest {
    private lateinit var presenter: MoviesContract.Presenter
    private lateinit var view: MoviesContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MoviesPresenter(view)
    }

    @Test
    fun `영화 목록을 불러온다`() {
        // Given: view의 setScreen 동작을 설정한다
        every { view.showScreen(any()) } just Runs

        // When: presenter가 데이터를 불러온다
        presenter.fetchData()

        // Then: view에 setScreen이 호출되어야 한다
        verify { view.showScreen(any()) }
    }
}
