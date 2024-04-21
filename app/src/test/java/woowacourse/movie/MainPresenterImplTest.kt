package woowacourse.movie

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.presenter.MainContract
import woowacourse.movie.presenter.MainPresenterImpl

class MainPresenterImplTest {
    private lateinit var mainView: MainContract.View
    private lateinit var mainPresenterImpl: MainPresenterImpl

    @BeforeEach
    fun setUp() {
        mainView = mockk(relaxed = true)
        mainPresenterImpl = MainPresenterImpl(mainView)
    }

    @Test
    fun fetchMovies() {
        mainPresenterImpl.fetchMovies()
        verify { mainView.updateMovieList(any()) }
    }
}
