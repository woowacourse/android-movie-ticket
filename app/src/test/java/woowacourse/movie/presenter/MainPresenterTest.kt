package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.extension.ImageResource
import woowacourse.movie.feature.movieSelect.MainView
import woowacourse.movie.feature.movieSelect.MovieSelectPresenter
import woowacourse.movie.model.movieSelect.screening.Screening
import java.time.LocalDate

class MainPresenterTest {
    private lateinit var movieSelectPresenter: MovieSelectPresenter
    private lateinit var mainView: MainView

    @BeforeEach
    fun setUp() {
        mainView = mockk(relaxed = true)
        movieSelectPresenter = MovieSelectPresenter(mainView)
    }

    @Test
    fun `기본 상영 목록으로 영화 리스트 UI를 초기화한다`() {
        // When
        movieSelectPresenter.loadMovieList()

        // Then
        verify { mainView.initMovieListUI(any(), any()) }
    }

    @Test
    fun `영화와 상영 목록과 포스터 정보를 사용해 예약 UI로 이동한다`() {
        // Given
        val start = LocalDate.of(2025, 4, 1)
        val end = LocalDate.of(2025, 4, 10)
        val screening = mockk<Screening>()
        val poster = mockk<ImageResource>()

        every { screening.title } returns "영화 제목"
        every { screening.movieId } returns 1L.toString()
        every { screening.period.start } returns start
        every { screening.period.endInclusive } returns end
        every { screening.runningTime } returns 120

        // When
        movieSelectPresenter.onMovieSelected(screening, poster)

        // Then
        verify {
            mainView.navigateToReservationUI(
                match {
                    it.title == "영화 제목" &&
                        it.movieId == 1L.toString() &&
                        it.runningTime == 120 &&
                        it.startDate == start &&
                        it.endDate == end &&
                        it.poster == poster
                },
            )
        }
    }
}
