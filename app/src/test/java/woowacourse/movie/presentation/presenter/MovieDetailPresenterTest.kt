package woowacourse.movie.presentation.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.contract.MovieDetailContract

class MovieDetailPresenterTest {
    private lateinit var view: MovieDetailContract.View
    private lateinit var presenter: MovieDetailPresenterImpl
    private lateinit var movieRepository: MovieRepository

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        movieRepository = mockk(relaxed = true)

        presenter = MovieDetailPresenterImpl(0, movieRepository)
        presenter.attachView(view)
    }

    @Test
    fun `View가 만들어지면 영화 상세 정보 출력을 요청한다`() {
        // when
        presenter.onViewSetUp()

        // then
        verify { view.showMovieDetail(any()) }
    }

    @Test
    fun `View가 만들어지면 상영 날짜와 시간 스피너 설정을 요청한다`() {
        every { movieRepository.getScreeningDateInfo(any()) } returns listOf()
        every { movieRepository.getScreeningTimeInfo(any()) } returns listOf()
        every { presenter.loadScreeningDates(any()) } just runs
        every { presenter.loadScreeningTimes(any()) } just runs

        // when
        presenter.onViewSetUp()

        // then
        verify { view.setScreeningDatesAndTimes(any(), any(), any()) }
    }

    @Test
    fun `날짜를 선택하면 새로운 시간 스피너 설정을 요청한다`() {
        // when
        presenter.selectDate("2024-04-06")

        every { presenter.loadScreeningTimes(any()) } just runs

        // then
        verify { view.updateScreeningTimes(any(), any()) }
    }

    @Test
    fun `예매 인원 감소 버튼을 누르면 view에게 변경된 예매 수를 보여줄 것을 요청한다`() {
        // given
        presenter.plusReservationCount()

        // when
        presenter.minusReservationCount()

        // then
        verify { view.showReservationCount(1) }
    }

    @Test
    fun `예매 인원 증가 버튼을 누르면 view에게 변경된 예매 수를 보여줄 것을 요청한다`() {
        // when
        presenter.plusReservationCount()

        // then
        verify { view.showReservationCount(2) }
    }

    @Test
    fun `예매 버튼을 누르면 view에게 좌석 선택 화면으로 넘어갈 것을 요청한다`() {
        // when
        presenter.onReserveButtonClicked()

        // then
        verify { view.moveToSeatSelection(1, any()) }
    }
}
