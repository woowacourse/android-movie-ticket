package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import woowacourse.movie.movie.Item
import woowacourse.movie.movie.MovieListContract
import woowacourse.movie.movie.MovieListPresenter
import kotlin.test.Test

class MovieListPresenterTest {
    private lateinit var view: MovieListContract.View
    private lateinit var presenter: MovieListPresenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieListContract.View>(relaxed = true)
        presenter = MovieListPresenter(view)
    }

    @Test
    fun `3번째마다 광고를 포함한 리스트를 전달한다`() {
        // when
        presenter.initMovies()

        // then
        verify {
            view.showMovies(
                match {
                    it.withIndex().all { (index, item) ->
                        if ((index + 1) % 4 == 0) {
                            item is Item.AdvertisementItem
                        } else {
                            true
                        }
                    }
                },
            )
        }
    }
}
