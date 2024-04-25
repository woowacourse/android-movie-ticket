package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainPresenterTest {
    private lateinit var mainView: MainContract.View
    private lateinit var mainPresenter: MainPresenter

    @BeforeEach
    fun setUp() {
        mainView = mockk(relaxed = true)
        mainPresenter = MainPresenter(mainView)
    }

    @Test
    fun fetchMovies() {
        mainPresenter.fetchMovies()
        verify { mainView.updateMovieList(any()) }
    }
}
