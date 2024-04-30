package woowacourse.movie.presentation.ui.detail

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.repository.MovieRepositoryImpl
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningSchedule
import woowacourse.movie.presentation.uimodel.MovieUiModel


class MovieDetailPresenterTest {
    private lateinit var movies: List<Movie>
    private lateinit var view: FakeMovieDetailView
    private lateinit var presenter: MovieDetailPresenter
    private lateinit var screeningSchedule: ScreeningSchedule
    
    @BeforeEach
    fun setUp() {
        movies = MovieRepositoryImpl.getAllMovies()
        view = FakeMovieDetailView()
        presenter = MovieDetailPresenter(view, MovieRepositoryImpl, MovieTicketRepositoryImpl, 1)
        screeningSchedule = ScreeningSchedule()
    }
    
    @Test
    fun `영화 상세 정보를 불러온다`() {
        assertThat(view.fakeMovieUiModel.title).isEqualTo(MovieUiModel.fromMovie(movies[0]).title)
        assertThat(view.fakeMovieUiModel.screeningDates).isEqualTo(MovieUiModel.fromMovie(movies[0]).screeningDates)
        assertThat(view.fakeMovieUiModel.runningTime).isEqualTo(MovieUiModel.fromMovie(movies[0]).runningTime)
        assertThat(view.fakeMovieUiModel.summary).isEqualTo(MovieUiModel.fromMovie(movies[0]).summary)
    }
    
    @Test
    fun `영화 상영 날짜를 불러온다`() {
        presenter.loadMovieScheduleDates(MovieUiModel.fromMovie(movies[0]).screeningDates)
        assertThat(view.fakeDates).isEqualTo(MovieUiModel.fromMovie(movies[0]).screeningDates)
    }
    
    @Test
    fun `영화 상영 시간을 불러온다`() {
        presenter.updateMovieScheduleDate(MovieUiModel.fromMovie(movies[0]).screeningDates.min())
        assertThat(view.fakeTimes).isEqualTo(screeningSchedule.times(movies[0].screeningDates.min()))
    }
    
    @Test
    fun `예약 인원이 1명일 때 증가시키면 2명이다`() {
        presenter.plusReservationCount()
        assertThat(view.fakeReservationCount).isEqualTo(2)
    }
    
    
    @Test
    fun `예약 인원을 2명일 때 감소시키면 1명이다`() {
        presenter.plusReservationCount()
        presenter.minusReservationCount()
        assertThat(view.fakeReservationCount).isEqualTo(1)
    }
    
    @Test
    fun `예약 인원이 1명일 때 감소시키면 1명이다`() {
        presenter.minusReservationCount()
        assertThat(view.fakeReservationCount).isEqualTo(1)
    }
    
    @Test
    fun `예약 인원이 20명일 때 증가시키면 20명이다`() {
        repeat(20) {
            presenter.plusReservationCount()
        }
        assertThat(view.fakeReservationCount).isEqualTo(20)
    }
}
