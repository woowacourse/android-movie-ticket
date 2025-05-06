package woowacourse.movie.presenterTest

import io.mockk.mockk
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.movieList.MovieListContract
import woowacourse.movie.movieList.MovieListPresenter
import woowacourse.movie.uiModel.AdUIModel
import woowacourse.movie.uiModel.MovieInfoUIModel
import woowacourse.movie.uiModel.MovieListItem

class MovieListPresenterTest {
    private lateinit var view: MovieListContract.View
    private lateinit var presenter: MovieListContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = MovieListPresenter(view)
    }

    @Test
    fun `뷰를_생성하면_영화들이_뷰에_들어간다`() {
        presenter.loadMovies()
        val exptedItem: MutableList<MovieListItem> =
            mutableListOf(
                MovieInfoUIModel(
                    "harry_potter_poster_1",
                    "해리 포터와 마법사의 돌",
                    "2025.4.1",
                    "2025.4.25",
                    152,
                ),
                MovieInfoUIModel(
                    "harry_potter_poster_2",
                    "해리 포터와 비밀의 방",
                    "2025.4.1",
                    "2025.4.28",
                    162,
                ),
                MovieInfoUIModel(
                    "harry_potter_poster_3",
                    "해리 포터와 아즈카반의 죄수",
                    "2025.5.1",
                    "2025.5.31",
                    141,
                ),
                AdUIModel,
                MovieInfoUIModel(
                    "harry_potter_poster_4",
                    "해리 포터와 불의 잔",
                    "2025.6.1",
                    "2025.6.30",
                    157,
                ),
            )
        verifySequence {
            view.showMovies(exptedItem)
        }
    }

    @Test
    fun `버튼을_클릭하면_영화정보를_가지고_액티비티가_바뀐다`() {
        val expectedItem =
            MovieInfoUIModel(
                "harry_potter_poster_1",
                "해리 포터와 마법사의 돌",
                "2025.4.1",
                "2025.4.25",
                152,
            )

        presenter.onMovieSelected(expectedItem)

        verifySequence {
            view.navigateToBooking(expectedItem)
        }
    }
}
