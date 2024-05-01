package woowacourse.movie.presentation.reservation.booking

import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.presentation.screening.ScreeningMovieUiModel
import woowacourse.movie.presentation.screening.stubScreenMovie
import woowacourse.movie.repository.MovieRepository

@ExtendWith(MockKExtension::class)
class MovieReservationPresenterTest {
    @RelaxedMockK
    private lateinit var view: MovieReservationView

    @RelaxedMockK
    private lateinit var repository: MovieRepository

    @InjectMockKs
    private lateinit var presenter: MovieReservationPresenter

    @Test
    fun `상영중인 영화 정보 가져오면, 영화 정보, 시간, 인원을 보여준다`() {
        // given
        val id = 1L
        val idSlot = slot<Long>()
        val screenMovie = stubScreenMovie()
        val expectReservationUiState = screenMovie.toReservationUiState()
        val screenMovieSlot = slot<ScreeningMovieUiModel>()
        val screenDatesSlot = slot<List<String>>()
        val screenTimesSlot = slot<List<String>>()
        val headCountSlot = slot<Int>()
        every { repository.screenMovieById(capture(idSlot)) } returns screenMovie
        every { view.showMovieReservation(capture(screenMovieSlot)) } just Runs
        every { view.showDatePicker(capture(screenDatesSlot)) } just Runs
        every { view.showTimePicker(capture(screenTimesSlot)) } just Runs
        every { view.showHeadCount(capture(headCountSlot)) } just Runs
        // when
        presenter.loadScreenMovie(id)
        // then
        presenter.uiState shouldBe expectReservationUiState
        verify { repository.screenMovieById(idSlot.captured) }
        verify(exactly = 0) { view.showErrorView() }
        verify { view.showMovieReservation(screenMovieSlot.captured) }
        verify { view.showDatePicker(screenDatesSlot.captured) }
        verify { view.showTimePicker(screenTimesSlot.captured) }
        verify { view.showHeadCount(headCountSlot.captured) }
    }

    @Test
    fun `상영중인 영화 정보 가져오면, 에러 화면을 보여준다`() {
        // given
        val id = 1L
        val idSlot = slot<Long>()
        every { repository.screenMovieById(capture(idSlot)) } returns null
        every { view.showErrorView() } just Runs
        // when
        presenter.loadScreenMovie(id)
        // then
        verify { repository.screenMovieById(idSlot.captured) }
        verify { view.showErrorView() }
        verify(exactly = 0) { view.showMovieReservation(any()) }
        verify(exactly = 0) { view.showDatePicker(any()) }
        verify(exactly = 0) { view.showTimePicker(any()) }
        verify(exactly = 0) { view.showHeadCount(any()) }
    }

    @Test
    fun `인원수가 1일 때, 인원수를 증가시키면 증가된 인원수를 화면에 보여준다`() {
        // given
        val headCountSlot = slot<Int>()
        every { view.showHeadCount(capture(headCountSlot)) } just Runs
        // when
        presenter.plusCount()
        // then
        headCountSlot.captured shouldBe presenter.uiState.headCount.count
        verify { view.showHeadCount(headCountSlot.captured) }
    }

    @Test
    fun `인원 수가 1일 때 감소시키면, 아무 변화가 없다`() {
        // when
        presenter.minusCount()
        // then
        verify(exactly = 0) { view.showHeadCount(any()) }
    }

    @Test
    fun `인원수가 1일 때, 인원수를 증가시켜 2로 만들고 화면에 보여준 후, 감소시켜 1로 만들고 화면에 보여준다`() {
        // given
        val expectedPlus = 2
        val expectedMinus = 1
        val headCountSlot = slot<Int>()
        every { view.showHeadCount(capture(headCountSlot)) } just Runs
        // when
        presenter.plusCount()
        // then
        headCountSlot.captured shouldBe expectedPlus
        verify { view.showHeadCount(headCountSlot.captured) }
        // when
        presenter.minusCount()
        // then
        headCountSlot.captured shouldBe expectedMinus
        verify { view.showHeadCount(headCountSlot.captured) }
    }

    @Test
    fun `영화 예매 화면의 상태를 복구시킨 후, 화면을 복구시킨다`() {
        // given
        val uiState = stubMovieReservationUiState()
        val screenMovieSlot = slot<ScreeningMovieUiModel>()
        val screenDatesSlot = slot<List<String>>()
        val screenTimesSlot = slot<List<String>>()
        val headCountSlot = slot<Int>()
        val selectedTimePositionSlot = slot<Int>()
        val selectedDatePositionSlot = slot<Int>()
        every { view.showMovieReservation(capture(screenMovieSlot)) } just Runs
        every { view.showDatePicker(capture(screenDatesSlot)) } just Runs
        every { view.showTimePicker(capture(screenTimesSlot)) } just Runs
        every { view.showHeadCount(capture(headCountSlot)) } just Runs
        every { view.showScreenDateAt(capture(selectedDatePositionSlot)) } just Runs
        every { view.showTimePickerAt(capture(selectedTimePositionSlot)) } just Runs
        // when
        presenter.restoreState(uiState)
        // then
        uiState shouldBe presenter.uiState
        verify { view.showMovieReservation(screenMovieSlot.captured) }
        verify { view.showDatePicker(screenDatesSlot.captured) }
        verify { view.showTimePicker(screenTimesSlot.captured) }
        verify { view.showHeadCount(headCountSlot.captured) }
        verify { view.showTimePickerAt(selectedTimePositionSlot.captured) }
        verify { view.showScreenDateAt(selectedDatePositionSlot.captured) }
    }
}
