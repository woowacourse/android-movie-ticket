package woowacourse.movie.feature.reservation

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.feature.setUpReservationCount
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl
import woowacourse.movie.model.data.dto.Movie
import java.time.LocalDateTime

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
        val movieSlot = slot<Movie>()
        every { view.initializeReservationView(capture(movieSlot)) } just runs
        every { view.initializeSpinner(any(), any()) } just runs

        // when
        presenter.loadMovieData(0L)

        // then
        val actual = movieSlot.captured
        assertThat(actual.id).isEqualTo(0L)
        verify { view.initializeReservationView(actual) }
        verify { view.initializeSpinner(any(), any()) }
    }

    @Test
    fun `예약 인원을 초기화하면 1명이 된다`() {
        // given
        val reservationCountSlot = slot<Int>()
        every { view.updateReservationCount(capture(reservationCountSlot)) } just runs

        // when
        presenter.loadReservationCount()

        // then
        val actual = reservationCountSlot.captured
        assertThat(actual).isEqualTo(1)
        verify { view.updateReservationCount(actual) }
    }

    @Test
    fun `예약 인원이 3명일 때 인원을 감소시키면 2명이 된다`() {
        // given
        val reservationCountSlot = slot<Int>()
        every { view.updateReservationCount(capture(reservationCountSlot)) } just runs
        presenter.setUpReservationCount(3)

        // when
        presenter.decreaseReservationCount()

        // then
        val actual = reservationCountSlot.captured
        assertThat(actual).isEqualTo(2)
        verify { view.updateReservationCount(actual) }
    }

    @Test
    fun `예약 인원이 1명일 때 인원을 감소시키면 변화가 없다`() {
        // given
        val reservationCountSlot = slot<Int>()
        every { view.updateReservationCount(capture(reservationCountSlot)) } just runs
        presenter.setUpReservationCount(1)

        // when
        presenter.decreaseReservationCount()

        // then
        val actual = reservationCountSlot.captured
        assertThat(actual).isEqualTo(1)
        verify { view.updateReservationCount(actual) }
    }

    @Test
    fun `예약 인원이 3명일 때 인원을 증가시키면 4명이 된다`() {
        // given
        val reservationCountSlot = slot<Int>()
        every { view.updateReservationCount(capture(reservationCountSlot)) } just runs
        presenter.setUpReservationCount(3)

        // when
        presenter.increaseReservationCount()

        // then
        val actual = reservationCountSlot.captured
        assertThat(actual).isEqualTo(4)
        verify { view.updateReservationCount(actual) }
    }

    @Test
    fun `예약 인원이 20명일 때 인원을 증가시키면 변화가 없다`() {
        // given
        val reservationCountSlot = slot<Int>()
        every { view.updateReservationCount(capture(reservationCountSlot)) } just runs
        presenter.setUpReservationCount(20)

        // when
        presenter.increaseReservationCount()

        // then
        val actual = reservationCountSlot.captured
        assertThat(actual).isEqualTo(20)
        verify { view.updateReservationCount(actual) }
    }

    @Test
    fun `좌석 선택 버튼을 누르면 해당 화면으로 이동한다`() {
        // given
        val screeningLocalDateTimeSlot = slot<LocalDateTime>()
        every { view.moveSeatSelectView(capture(screeningLocalDateTimeSlot), any()) } just runs
        every { view.updateReservationCount(any()) } just runs
        presenter.loadReservationCount()

        // when
        presenter.selectSeat("2024-4-27", "11:00")

        // then
        val actual = screeningLocalDateTimeSlot.captured
        assertThat(actual).isEqualTo(LocalDateTime.of(2024, 4, 27, 11, 0))
        verify { view.moveSeatSelectView(actual, any()) }
    }

    @Test
    fun `예약 인원을 4명으로 변경하면 4명이 된다`() {
        // given
        val reservationCountSlot = slot<Int>()
        every { view.updateReservationCount(capture(reservationCountSlot)) } just runs
        presenter.loadReservationCount()

        // when
        presenter.updateReservationCount(4)

        // then
        val actual = reservationCountSlot.captured
        assertThat(actual).isEqualTo(4)
        verify { view.updateReservationCount(actual) }
    }

    @Test
    fun `예약 인원을 30명으로 변경하면 에러 화면을 표시한다`() {
        // given
        val movieReservationErrorSlot = slot<MovieReservationError>()
        every { view.handleError(capture(movieReservationErrorSlot)) } just runs
        every { view.updateReservationCount(any()) } just runs
        presenter.loadReservationCount()

        // when
        presenter.updateReservationCount(30)

        // then
        val actual = movieReservationErrorSlot.captured
        assertThat(actual).isEqualTo(MovieReservationError.ReservationCountRange)
        verify { view.handleError(actual) }
    }

    @Test
    fun `상영 날짜를 선택하면 상영 시간이 변경된다`() {
        // given
        val screeningTimesSlot = slot<List<String>>()
        every { view.updateScreeningTimeSpinner(capture(screeningTimesSlot)) } just runs

        // when (주말)
        presenter.selectScreeningDate("2024-4-27")

        // then
        val actual = screeningTimesSlot.captured
        val expected = listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
        assertThat(actual).isEqualTo(expected)
        verify { view.updateScreeningTimeSpinner(actual) }
    }
}
