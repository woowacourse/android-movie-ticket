package woowacourse.movie.presentation.detail

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.MOVIE
import woowacourse.movie.domain.MovieRepository
import java.time.LocalDate
import java.time.LocalTime

@ExtendWith(MockKExtension::class)
class MovieDetailPresenterTest {
    @MockK
    private lateinit var detailContractView: MovieDetailContract.View

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var detailPresenter: MovieDetailPresenter

    @BeforeEach
    fun setUp() {
        detailPresenter = MovieDetailPresenter(detailContractView, movieRepository)
    }

    @Test
    fun `정상적인 인덱스의 loadMovie를 호출하여 뷰와 어댑터를 초기화한다`() {
        every { movieRepository.findMovieById(any()) } returns MOVIE
        every { detailContractView.onUpdateView(any()) } just runs
        every { detailContractView.onUpdateDate(any(), any()) } just runs
        every { detailContractView.onUpdateTime(any(), any()) } just runs
        every { detailContractView.onError(any()) } just runs

        detailPresenter.loadMovie(0)

        verify { detailContractView.onUpdateView(any()) }
        verify { detailContractView.onUpdateDate(any(), any()) }
        verify { detailContractView.onUpdateTime(any(), any()) }
        verify(exactly = 0) { detailContractView.onError(any()) }
    }

    @Test
    fun `데이터가 없는 인덱스의 loadMovie를 호출하여 뷰와 어댑터를 초기화한다`() {
        every { movieRepository.findMovieById(any()) } returns null
        every { detailContractView.onUpdateView(any()) } just runs
        every { detailContractView.onUpdateDate(any(), any()) } just runs
        every { detailContractView.onUpdateTime(any(), any()) } just runs
        every { detailContractView.onError(any()) } just runs

        detailPresenter.loadMovie(-1)

        verify(exactly = 0) { detailContractView.onUpdateView(any()) }
        verify(exactly = 0) { detailContractView.onUpdateDate(any(), any()) }
        verify(exactly = 0) { detailContractView.onUpdateTime(any(), any()) }
        verify(exactly = 1) { detailContractView.onError(any()) }
    }

    @Test
    fun `plus 클릭 시 onUpdateView가 호출된다`() {
        every { detailContractView.onUpdateView(any()) } just runs

        detailPresenter.plusReservationCount()

        verify { detailContractView.onUpdateView(any()) }
    }

    @Test
    fun `minus 클릭 시 onUpdateView가 호출된다`() {
        every { detailContractView.onUpdateView(any()) } just runs

        detailPresenter.minusReservationCount()

        verify { detailContractView.onUpdateView(any()) }
    }

    @Test
    fun `날짜를 선택하면, 시간 데이터가 생성되어 view에게 전달된다`() {
        every { detailContractView.onUpdateTime(any(), any()) } just runs
        val now = LocalDate.now()

        detailPresenter.selectDate(now, 0)

        verify { detailContractView.onUpdateTime(any(), any()) }
    }

    @Test
    fun `좌석 선택을 요청하면, 해당 페이지로 이동하기 위한 view의 함수가 호출된다`() {
        every { detailContractView.onSelectSeatClicked(any(), any(), any()) } just runs

        detailPresenter.selectSeat(LocalDate.now(), LocalTime.now())

        verify { detailContractView.onSelectSeatClicked(any(), any(), any()) }
    }
}
