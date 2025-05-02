package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieStore
import woowacourse.movie.domain.fixture.mayFourFixture
import woowacourse.movie.domain.fixture.mayOneFixture
import woowacourse.movie.domain.fixture.mayThreeFixture
import woowacourse.movie.domain.fixture.mayTwoFixture
import woowacourse.movie.domain.fixture.moviesFixture
import woowacourse.movie.domain.fixture.screeningDateFixture
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.booking.BookingContract
import woowacourse.movie.view.booking.BookingPresenter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenterTest {
    private val view = mockk<BookingContract.View>(relaxed = true)
    private lateinit var model: MovieStore
    private lateinit var presenter: BookingPresenter

    @BeforeEach
    fun setUp() {
        model = MovieStore()
        presenter =
            BookingPresenter(
                view = view,
                selectedMovie = moviesFixture[0],
                count = PeopleCount(1),
            )
    }

    @Test
    fun `영화 정보를 UI에 표시한다`() {
        // when
        presenter.loadMovieDetail()

        val expected =
            Movie(
                id = 0,
                title = "Movie 1",
                posterResource = "poster1",
                releaseDate = screeningDateFixture,
                runningTime = 120,
            )

        // then
        verify {
            view.showMovieDetail(
                match {
                    it.id == expected.id &&
                        it.title == expected.title &&
                        it.posterResource == expected.posterResource &&
                        it.releaseDate.startDate == expected.releaseDate.startDate &&
                        it.releaseDate.endDate == expected.releaseDate.endDate &&
                        it.runningTime == expected.runningTime
                },
            )
        }
    }

    @Test
    fun `loadPeopleCount 호출시 최소 인원수인 한 명이 보인다`() {
        // when
        presenter.loadMovieDetail()

        // then
        verify(exactly = 1) { view.showPeopleCount(1) }
    }

    @Test
    fun `인원은 0명이 될 수 없다`() {
        // given
        every { view.showPeopleCount(1) } just Runs

        // when
        presenter.decreasePeopleCount()

        // then
        verify { view.showPeopleCount(1) }
    }

    @Test
    fun `인원이 1명 증가한다`() {
        // given
        every { view.showPeopleCount(1) }

        // when
        presenter.increasePeopleCount(2)

        // then
        verify { view.showPeopleCount(2) }
    }

    @Test
    fun `인원이 1명 감소한다`() {
        // given
        val presenter =
            BookingPresenter(view, moviesFixture[0], PeopleCount(5))
        every { view.showPeopleCount(4) } just Runs

        // when
        presenter.decreasePeopleCount()

        // then
        verify { view.showPeopleCount(4) }
    }

    @Test
    fun `예약 버튼을 누르면 예약 완료 화면으로 이동하며 예약 정보 Booking 객체를 전달한다`() {
        // when
        presenter.loadBooking(
            title = "테스트 영화 1",
            bookingDate = "2025-04-24",
            bookingTime = "12:00",
            count = "3",
        )

        val expected =
            Booking(
                title = "테스트 영화 1",
                bookingDate = LocalDate.of(2025, 4, 24),
                bookingTime = LocalTime.of(12, 0),
                count = PeopleCount(3),
            )

        // then
        verify {
            view.moveToBookingComplete(
                match {
                    it.title == expected.title &&
                        it.bookingDate == expected.bookingDate &&
                        it.bookingTime == expected.bookingTime &&
                        it.count == expected.count
                },
            )
        }
    }

    @Test
    fun `영화 상열 시작일과 종료일, 현재 날짜를 전달하면 상영 시작일과 종료일 사이의 날짜를 출력한다 `() {
        // when
        presenter.loadMovieDetail()

        // then
        val expected =
            listOf(
                mayOneFixture,
                mayTwoFixture,
                mayThreeFixture,
                mayFourFixture,
            )
        verify { view.showScreeningDate(expected) }
    }

    @Test
    fun `오늘 날짜가 상영 시작을 지났다면 오늘 날짜부터 상영 종료일까지 출력한다`() {
        // given
        val now = LocalDateTime.of(2025, 5, 3, 12, 0, 0)
        presenter = BookingPresenter(view, moviesFixture[0], PeopleCount(1), now)

        // when
        presenter.loadMovieDetail()

        // then
        val expected =
            listOf(
                mayThreeFixture,
                mayFourFixture,
            )
        verify { view.showScreeningDate(expected) }
    }

    @Test
    fun `현재 시간 이후의 상영 시간을 출력한다`() {
        // given
        val date = LocalDate.of(2025, 4, 25)
        val now = LocalDateTime.of(2025, 4, 25, 20, 0, 0)

        // when
        presenter.loadScreeningTime(date, now)

        // then
        val expected = listOf(LocalTime.of(22, 0))
        verify { view.showScreeningTime(expected) }
    }
}
