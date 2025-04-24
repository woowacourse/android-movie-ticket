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
import woowacourse.movie.view.booking.BookingContract
import woowacourse.movie.view.booking.BookingPresenter
import java.time.LocalDate
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
}
