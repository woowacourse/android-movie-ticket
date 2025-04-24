package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.fakeMoviesModel
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.booking.TicketType
import woowacourse.movie.view.StringFormatter.dotDateFormat
import woowacourse.movie.view.booking.BookingContract
import woowacourse.movie.view.booking.BookingPresenter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenterTest {
    private lateinit var view: BookingContract.View
    private lateinit var presenter: BookingPresenter

    @BeforeEach
    fun setUp() {
        view = mockk<BookingContract.View>(relaxed = true)
        presenter = BookingPresenter(view, fakeMoviesModel, PeopleCount(1))
    }

    @Test
    fun `영화 정보를 UI에 표시한다`() {
        // given
        val movieIndex = 0
        val movie = fakeMoviesModel[movieIndex]

        // when
        presenter.loadMovieDetail(movieIndex)

        // then
        verify {
            view.showMovieDetail(
                title = movie.title,
                posterResId = movie.posterResource.posterId,
                releaseStartDate = dotDateFormat(movie.releaseDate.startDate),
                releaseEndDate = dotDateFormat(movie.releaseDate.endDate),
                runningTime = movie.runningTime,
            )
        }
    }

    @Test
    fun `loadPeopleCount 호출시 최소 인원수인 한 명이 보인다`() {
        // when
        presenter.loadPeopleCount()

        // then
        verify(exactly = 1) { view.showPeopleCount(1) }
    }

    @Test
    fun `인원은 0명이 될 수 없다`() {
        // given
        var count = 1
        every { view.showPeopleCount(count) } just Runs

        // when
        presenter.decreasePeopleCount()

        // then
        verify { view.showPeopleCount(1) }
    }

    @Test
    fun `인원이 1명 증가한다`() {
        // given
        var count = 1
        every { view.showPeopleCount(count) } just Runs

        // when
        presenter.increasePeopleCount()

        // then
        verify { view.showPeopleCount(2) }
    }

    @Test
    fun `인원이 1명 감소한다`() {
        // given
        val presenter = BookingPresenter(view, fakeMoviesModel, PeopleCount(5))
        every { view.showPeopleCount(4) } just Runs

        // when
        presenter.decreasePeopleCount()

        // then
        verify { view.showPeopleCount(4) }
    }

    @Test
    fun `예약 버튼을 누르면 예얀 완료 화면으로 이동하며 예약 정보 Booking 객체를 전달한다`() {
        // given
        every { view.onClickBooking() } just Runs

        // when
        presenter.loadBooking(
            title = "테스트 영화 1",
            bookingDate = "2025-04-24",
            bookingTime = "12:00",
            count = "3",
            ticketType = TicketType.GENERAL,
        )

        // then
        verify {
            view.moveToBookingComplete(
                match {
                    it.title == "테스트 영화 1" &&
                        it.bookingDate == LocalDate.of(2025, 4, 24) &&
                        it.bookingTime == LocalTime.of(12, 0) &&
                        it.count == PeopleCount(3) &&
                        it.ticketType == TicketType.GENERAL
                },
            )
        }
    }

    @Test
    fun `영화 상열 시작일과 종료일, 현재 날짜를 전달하면 상영 시작일과 종료일 사이의 날짜를 출력한다 `() {
        // given
        val start = "2025.4.26"
        val end = "2025.4.28"
        val now = LocalDateTime.of(2025, 4, 25, 12, 0, 0)

        // when
        presenter.loadScreeningDate(start, end, now)

        // then
        val expected = listOf("2025-04-26", "2025-04-27", "2025-04-28")
        verify { view.showScreeningDate(expected) }
    }

    @Test
    fun `오늘 날짜가 상영 시작을 지났다면 오늘 날짜부터 상영 종료일까지 출력한다`() {
        // given
        val start = "2025.4.24"
        val end = "2025.4.28"
        val now = LocalDateTime.of(2025, 4, 25, 12, 0, 0)

        // when
        presenter.loadScreeningDate(start, end, now)

        // then
        val excepted = listOf("2025-04-25", "2025-04-26", "2025-04-27", "2025-04-28")
        verify { view.showScreeningDate(excepted) }
    }

    @Test
    fun `현재 시간 이후의 상영 시간을 출력한다`() {
        // given
        val date = "2025-04-25"
        val now = LocalDateTime.of(2025, 4, 25, 20, 0, 0)

        // when
        presenter.loadScreeningTime(date, now)

        // then
        val expected = listOf("22:00")
        verify { view.showScreeningTime(expected) }
    }
}
