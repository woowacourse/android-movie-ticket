package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.presenter.contract.MovieListContract

class MovieListPresenterTest {
    private lateinit var view: MovieListContract.View
    private lateinit var presenter: MovieListContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieListContract.View>()
        presenter = MovieListPresenter(view)
    }

    @Test
    fun `상영_정보를_표시한다`() {
        // given
        every { view.initializeScreeningList(any(), any()) } just runs
        // when
        presenter.loadScreeningData()
        // then
        verify {
            view.initializeScreeningList(any(), any())
        }
    }
}
