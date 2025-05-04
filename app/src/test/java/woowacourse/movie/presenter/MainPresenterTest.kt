package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.movieSelect.MovieSelectContract
import woowacourse.movie.feature.movieSelect.MovieSelectPresenter
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData

class MainPresenterTest {
    private lateinit var movieSelectPresenter: MovieSelectContract.Presenter
    private lateinit var movieSelectView: MovieSelectContract.View

    @BeforeEach
    fun setUp() {
        movieSelectView = mockk(relaxed = true)
        movieSelectPresenter = MovieSelectPresenter(movieSelectView)
    }

    @Test
    fun `기본 상영 목록으로 영화 리스트 UI를 초기화한다`() {
        // When
        movieSelectPresenter.loadMovieList()

        // Then
        verify { movieSelectView.updateMovieList(any()) }
    }

    @Test
    fun `선택한 영화 정보를 사용해 예약 UI로 이동한다`() {
        // Given
        val screeningData = mockk<ScreeningData>()

        // When
        movieSelectPresenter.onMovieSelected(screeningData)

        // Then
        verify {
            movieSelectView.navigateToReservationView(
                screeningData,
            )
        }
    }
}
