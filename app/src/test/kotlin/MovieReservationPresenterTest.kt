import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import woowacourse.movie.data.DummyMovies
import woowacourse.movie.moviereservation.MovieReservationContract
import woowacourse.movie.moviereservation.MovieReservationPresenter
import woowacourse.movie.moviereservation.uimodel.HeadCountUiModel

class MovieReservationPresenterTest {
    private lateinit var view: MovieReservationContract.View

    private lateinit var presenter: MovieReservationContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieReservationContract.View>()
        presenter = MovieReservationPresenter(view, DummyMovies)
    }

    @Test
    @DisplayName("영화 정보를 불러오면 화면에 나타난다")
    fun show_movie_info_When_load_movie_data() {
        every { view.showMovieInfo(any()) } just Runs
        every { view.showDefaultBookingInfo(any(), any()) } just Runs

        presenter.loadMovieDetail(1)

        verify(exactly = 1) { view.showMovieInfo(any()) }
    }

    @Test
    @DisplayName("현재 예매 인원이 1일 때, 플러스 버튼을 누르면 인원이 2가 된다.")
    fun becomes_2_when_current_number_is_1_And_click_plus_button() {
        val updatedCount = HeadCountUiModel("2")
        every { view.updateHeadCount(updatedCount) } just Runs

        val currentCount = HeadCountUiModel("1")
        presenter.plusCount(currentCount)

        verify(exactly = 1) { view.updateHeadCount(updatedCount) }
    }

    @Test
    @DisplayName("현재 예매 인원이 2일 때, 마이너스 버튼을 누르면 인원이 1이 된다.")
    fun becomes_1_when_current_number_is_2_And_click_minus_button() {
        val updatedCount = HeadCountUiModel("1")
        every { view.updateHeadCount(updatedCount) } just Runs

        val currentCount = HeadCountUiModel("2")
        presenter.minusCount(currentCount)

        verify(exactly = 1) { view.updateHeadCount(updatedCount) }
    }

    @Test
    @DisplayName("현재 예매 인원이 1일 때, 마이너스 버튼을 눌러도 감소하지 않는다.")
    fun does_not_decrease_When_current_number_is_1_And_click_minus_button() {
        every { view.showCantDecreaseError(1) } just Runs

        val currentCount = HeadCountUiModel("1")
        presenter.minusCount(currentCount)

        verify(exactly = 1) { view.showCantDecreaseError(1) }
        verify(exactly = 0) { view.updateHeadCount(any()) }
    }
}
