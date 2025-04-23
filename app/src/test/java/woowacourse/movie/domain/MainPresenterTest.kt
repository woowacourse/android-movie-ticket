package woowacourse.movie.domain

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.view.movies.MainContract
import woowacourse.movie.view.movies.MainPresenter

class MainPresenterTest {
    private lateinit var view: MainContract.View
    private lateinit var presenter: MainContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MainPresenter(view)
    }

    @Test
    fun `데이터를 가져오면 화면에 띄워진다`() {
        // given
        every { view.showMoviesScreen(any()) } just Runs

        presenter.fetchData()

        // when & then
        verify { view.showMoviesScreen(any()) }
    }
}
