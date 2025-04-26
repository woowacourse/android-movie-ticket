package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.feature.bookingdetail.contract.BookingDetailContract
import woowacourse.movie.feature.bookingdetail.presenter.BookingDetailPresenter
import woowacourse.movie.feature.mapper.toUi
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieUiModel

class BookingDetailPresenterTest {
    private lateinit var presenter: BookingDetailPresenter
    private lateinit var view: BookingDetailContract.View

    private lateinit var movieUiModel: MovieUiModel
    private lateinit var bookingInfoUiModel: BookingInfoUiModel

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingDetailPresenter(view)

        movieUiModel =
            Movie(
                title = "레디 플레이어 원",
                startDate = MovieDate(2025, 5, 1),
                endDate = MovieDate(2025, 5, 10),
                runningTime = 148,
            ).toUi()

        bookingInfoUiModel =
            BookingInfoUiModel(
                movie = movieUiModel,
                date = movieUiModel.startDate,
                movieTime = MovieTime(9, 0).toUi(),
            )
    }

    @Test
    fun `onCreateView 호출 시 날짜, 시간, 예약정보를 갱신한다`() {
        // given & when
        presenter.onCreateView(movieUiModel)

        // then
        verify {
            view.setupDateView(any())
            view.setupTimeView(any())
            view.updateView(any())
        }
    }

    @Test
    fun `onDateSelected 호출 시 영화 시간을 갱신한다`() {
        // given
        presenter.onCreateView(movieUiModel)
        val dateString = movieUiModel.startDate.toString()

        // when
        presenter.onDateSelected(dateString)

        // then
        verify { view.updateTimeSpinnerItems(any()) }
    }

    @Test
    fun `onTicketCountIncreased 호출 시 티켓 수 증가 후 뷰의 출력을 갱신한다`() {
        // given
        presenter.onCreateView(movieUiModel)

        // when
        presenter.onTicketCountIncreased()

        // then
        verify { view.updateTicketCount(any()) }
    }

    @Test
    fun `onTicketCountDecreased 호출 시 티켓 수 감소 후 뷰의 출력을 갱신한다`() {
        // given
        presenter.onCreateView(movieUiModel)

        // when
        presenter.onTicketCountDecreased()

        // then
        verify { view.updateTicketCount(any()) }
    }

    @Test
    fun `onBookingCompleteButtonClicked 호출 시 좌석 선택 화면으로 이동한다`() {
        // given
        presenter.onCreateView(movieUiModel)

        // when
        presenter.onBookingCompleteButtonClicked()

        // then
        verify { view.navigateToBookingSeat(any()) }
    }

    @Test
    fun `onBackButtonClicked 호출 시 뒤로 이동한다`() {
        // given & when
        presenter.onBackButtonClicked()

        // then
        verify { view.navigateToBack() }
    }

    @Test
    fun `onSaveInstanceState 호출 시 현재 예약정보를 반환한다`() {
        // given
        presenter.onCreateView(movieUiModel)

        // when
        val savedState = presenter.onSaveInstanceState()

        // then
        assert(savedState.movie.title == movieUiModel.title)
    }

    @Test
    fun `onRestoreInstanceState 호출 시 기존에 저장된 예약정보로 복원한다`() {
        // given
        presenter.onCreateView(movieUiModel)

        // when
        presenter.onRestoreInstanceState(bookingInfoUiModel)

        // then
        verify { view.updateView(bookingInfoUiModel) }
    }
}
