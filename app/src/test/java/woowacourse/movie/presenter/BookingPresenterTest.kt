package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.booking.contract.BookingContract
import woowacourse.movie.ui.booking.presenter.BookingPresenter
import java.time.LocalDate

class BookingPresenterTest {
    private lateinit var view: BookingContract.View
    private lateinit var presenter: BookingPresenter

    private lateinit var dummyMovie: Movie
    private val dummySelectedDate = LocalDate.of(2025, 6, 1)

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = BookingPresenter(view)

        dummyMovie = Movie.DUMMY_MOVIES.first()

        every { view.getMovie() } returns dummyMovie
        every { view.getSelectedDate() } returns dummySelectedDate
    }

    @Test
    fun `인원 수가 증가하면 인원 수 텍스트가 업데이트 된다`() {
        presenter.increaseHeadcount()
        verify { view.updateHeadcountDisplay(any()) }
    }

    @Test
    fun `인원 수가 감소하면 인원 수 텍스트가 업데이트 된다`() {
        presenter.decreaseHeadcount()
        verify { view.updateHeadcountDisplay(any()) }
    }

    @Test
    fun `뷰에서 영화를 받아올 수 있다`() {
        presenter.loadMovie()
        verify { view.getMovie() }
    }

    @Test
    fun `날짜 스피너를 업데이트하면 뷰에 반영된다`() {
        presenter.setupDateSpinner()
        verify { view.setDateSpinner(any(), any()) }
    }

    @Test
    fun `시간 스피너를 업데이트하면 뷰에 반영된다`() {
        presenter.setupTimeSpinner()
        verify { view.setTimeSpinner(any(), any()) }
    }

    @Test
    fun `날짜 스피너를 업데이트하면 선택된 시간에 따라 시간 스피너가 업데이트 된다`() {
        presenter.setupDateSpinner()
        verify { view.setDateSpinner(any(), any()) }
    }

    @Test
    fun `날짜 스피너의 목록 포지션이 바뀌면 날짜 스피너가 업데이트 된다`() {
        val position = 1
        presenter.setSelectedDatePosition(position)
        verify { view.setDateSpinner(any(), position) }
    }

    @Test
    fun `시간 스피너의 목록 포지션이 바뀌면 시간 스피너가 업데이트 된다`() {
        val position = 2
        presenter.setSelectedTimePosition(position)
        verify { view.setTimeSpinner(any(), position) }
    }
}
