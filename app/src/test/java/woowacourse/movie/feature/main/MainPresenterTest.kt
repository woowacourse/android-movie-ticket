package woowacourse.movie.feature.main

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.feature.main.ui.toUiModel

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View
    private lateinit var repository: MovieRepository

    @BeforeEach
    fun setup() {
        view = mockk<MainContract.View>()
        repository = mockk<MovieRepository>()
        presenter = MainPresenter(view, repository)
    }

    @Test
    fun `리스트를 불러오면 `() {
        // given
        every { repository.findAll() } returns MOCK_MOVIES
        every { view.displayMovies(any()) } just runs
        // when
        presenter.fetchMovieList()
        // Then
        verify { view.displayMovies(MOCK_MOVIES.map { it.toUiModel() }) }
    }

    @Test
    fun `영화를 클릭하면 예약 화면으로 이동한다`() {
        // given
        every { view.navigateToReservationScreen(any()) } just runs
        // when
        presenter.selectMovie(0)
        // Then
        verify { view.navigateToReservationScreen(0) }
    }

    companion object {
        val MOCK_MOVIE: Movie =
            Movie(
                0,
                0,
                "제목",
                "설명",
                "날짜",
                0,
            )
        val MOCK_MOVIES = mutableListOf(MOCK_MOVIE)
    }
}
