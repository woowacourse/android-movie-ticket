package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.detailbooking.DetailBookingContract
import woowacourse.movie.detailbooking.DetailBookingPresenter
import woowacourse.movie.fixture.MOVIE
import woowacourse.movie.fixture.SELECTED_DATES
import woowacourse.movie.fixture.SELECTED_TIMES

class DetailBookingPresenterTest {
    private lateinit var view: DetailBookingContract.View
    private lateinit var presenter: DetailBookingContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = DetailBookingPresenter(view)

        every { view.showMovieData(any()) } just Runs
        every { view.showMovieSchedule(any(), any()) } just Runs
        every { view.showMovieScreeningTime(any(), any()) } just Runs
        every { view.showCount(any()) } just Runs
    }

    @Test
    fun `set 호출 시 영화 정보를 보여준다`() {
        presenter.set(MOVIE)

        verify { view.showMovieData(MOVIE) }
    }

    @Test
    fun `set 호출 시 예매 가능 날짜를 보여준다`() {
        presenter.set(MOVIE)

        verify { view.showMovieSchedule(SELECTED_DATES, 0) }
    }

    @Test
    fun `set 호출 시 최소 예매 인원를 보여준다`() {
        presenter.set(MOVIE)

        verify { view.showCount(1) }
    }

    @Test
    fun `clickedDate 호출 시 선택된 날짜을 보여준다`() {
        presenter.set(MOVIE)

        verify { view.showMovieScreeningTime(SELECTED_TIMES, 0) }
    }

    @Test
    fun `increasedCount 호출 시 증가된 인원을 보여준다`() {
        presenter.set(MOVIE)
        presenter.increasedCount()

        verify { view.showCount(2) }
    }

    @Test
    fun `decreasedCount 호출 시 감소된 인원을 보여준다`() {
        presenter.set(MOVIE)

        presenter.increasedCount()
        presenter.increasedCount()
        presenter.decreasedCount()

        verify { view.showCount(2) }
    }

    @Test
    fun `restoreState 호출 시 저장된 선택된 날짜를 보여준다`() {
        presenter.set(MOVIE)

        verify { view.showMovieSchedule(SELECTED_DATES, 0) }
    }

    @Test
    fun `restoreState 호출 시 저장된 선택된 시간을 보여준다`() {
        presenter.set(MOVIE)

        verify { view.showMovieScreeningTime(SELECTED_TIMES, 0) }
    }

    @Test
    fun `restoreState 호출 시 저장된 예매 인원를 보여준다`() {
        presenter.set(MOVIE)
        presenter.restoreState(2, 1, 1)

        verify { view.showCount(2) }
    }
}