package woowacourse.movie.feature.home

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl

class MovieHomePresenterTest {
    private lateinit var view: MovieHomeContract.View
    private val repository: MovieRepository = MovieRepositoryImpl
    private lateinit var presenter: MovieHomePresenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieHomeContract.View>()
        presenter = MovieHomePresenter(view, repository)
    }

    @Test
    fun `영화 데이터를 불러오면 영화 목록 뷰가 초기화된다`() {
        // given
        every { view.initializeMovieList(any()) } just runs

        // when
        presenter.loadMovieData()

        // then
        verify { view.initializeMovieList(any()) }
    }
}
