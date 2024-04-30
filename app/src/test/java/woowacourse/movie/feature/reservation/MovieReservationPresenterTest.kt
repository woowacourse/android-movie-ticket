package woowacourse.movie.feature.reservation

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.setUpReservationCount
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl

class MovieReservationPresenterTest {
    private lateinit var view: MovieReservationContract.View
    private val repository: MovieRepository = MovieRepositoryImpl
    private lateinit var presenter: MovieReservationPresenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieReservationContract.View>()
        presenter = MovieReservationPresenter(view, repository)
    }

    @Test
    fun `영화 데이터를 불러오면 영화 예매 뷰가 초기화된다`() {
        // given
        every { view.initializeReservationView(any()) } just runs
        every { view.initializeSpinner(any(), any()) } just runs

        // when
        presenter.loadMovieData(0L)

        // then
        verify { view.initializeReservationView(any()) }
        verify { view.initializeSpinner(any(), any()) }
    }

    @Test
    fun `예약 인원을 초기화하면 1명이 된다`() {
        // given
        every { view.updateReservationCount(any()) } just runs

        // when
        presenter.loadReservationCount()

        // then
        verify { view.updateReservationCount(1) }
    }

    @Test
    fun `예약 인원이 3명일 때 인원을 감소시키면 2명이 된다`() {
        // given
        every { view.updateReservationCount(any()) } just runs
        presenter.setUpReservationCount(3)

        // when
        presenter.decreaseReservationCount()

        // then
        verify { view.updateReservationCount(2) }
    }

    @Test
    fun `예약 인원이 1명일 때 인원을 감소시키면 변화가 없다`() {
        // given
        every { view.updateReservationCount(any()) } just runs
        presenter.setUpReservationCount(1)

        // when
        presenter.decreaseReservationCount()

        // then
        verify { view.updateReservationCount(1) }
    }

    @Test
    fun `예약 인원이 3명일 때 인원을 증가시키면 4명이 된다`() {
        // given
        every { view.updateReservationCount(any()) } just runs
        presenter.setUpReservationCount(3)

        // when
        presenter.increaseReservationCount()

        // then
        verify { view.updateReservationCount(4) }
    }

    @Test
    fun `예약 인원이 20명일 때 인원을 증가시키면 변화가 없다`() {
        // given
        every { view.updateReservationCount(any()) } just runs
        presenter.setUpReservationCount(20)

        // when
        presenter.increaseReservationCount()

        // then
        verify { view.updateReservationCount(20) }
    }

    @Test
    fun `좌석 선택 버튼을 누르면 해당 화면으로 이동한다`() {
        // given
        every { view.moveSeatSelectView(any(), any()) } just runs
        every { view.updateReservationCount(any()) } just runs
        presenter.loadReservationCount()

        // when
        presenter.selectSeat("2024-4-27", "11:00")

        // then
        verify { view.moveSeatSelectView(any(), any()) }
    }

    @Test
    fun `예약 인원을 4명으로 변경하면 4명이 된다`() {
        // given
        every { view.updateReservationCount(any()) } just runs
        presenter.loadReservationCount()

        // when
        presenter.updateReservationCount(4)

        // then
        verify { view.updateReservationCount(4) }
    }

    @Test
    fun `예약 인원을 30명으로 변경하면 에러 화면을 표시한다`() {
        // given
        every { view.handleError(any()) } just runs
        every { view.updateReservationCount(any()) } just runs
        presenter.loadReservationCount()

        // when
        presenter.updateReservationCount(30)

        // then
        verify { view.handleError(any()) }
    }

    @Test
    fun `상영 날짜를 선택하면 상영 시간이 변경된다`() {
        // given
        every { view.updateScreeningTimeSpinner(any()) } just runs

        // when
        presenter.selectScreeningDate("2024-4-27")

        // then
        verify { view.updateScreeningTimeSpinner(any()) }
    }
}
