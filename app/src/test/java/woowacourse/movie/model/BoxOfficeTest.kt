package woowacourse.movie.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.ticketing.BookingDateTime
import woowacourse.movie.model.ticketing.BookingSeat
import java.time.LocalDate
import java.time.LocalTime

class BoxOfficeTest {
    private lateinit var boxOffice: BoxOffice

    @BeforeEach
    fun setUp() {
        boxOffice =
            BoxOffice(
                Count(5),
                BookingDateTime(LocalDate.of(2024, 3, 1), LocalTime.of(11, 0)),
                emptyList(),
            )
    }

    @Test
    fun `매표소_데이터에_따른_올바른_가격을_반환한다`() {
        val boxOffice =
            BoxOffice(
                Count(5),
                BookingDateTime(LocalDate.of(2024, 3, 1), LocalTime.of(11, 0)),
                listOf(
                    BookingSeat(1, 1, SeatClass.B),
                    BookingSeat(2, 3, SeatClass.S),
                    BookingSeat(3, 3, SeatClass.S),
                    BookingSeat(4, 3, SeatClass.A),
                    BookingSeat(4, 1, SeatClass.A),
                ),
            )
        assertEquals(64000, boxOffice.totalPrice)
    }

    @Test
    fun `신청_인원만큼_좌석들이_선택되면_예약을_완료할_수_있다`() {
        // when
        boxOffice.updateSeats(
            listOf(
                BookingSeat(1, 1, SeatClass.B),
                BookingSeat(2, 3, SeatClass.S),
                BookingSeat(3, 3, SeatClass.S),
                BookingSeat(4, 3, SeatClass.A),
                BookingSeat(4, 1, SeatClass.A),
            ),
        )

        // then
        assertEquals(true, boxOffice.isSubmitAvailable)
    }

    @Test
    fun `신청_인원만큼_좌석들이_선택되지 못하면_예약을_완료할_수_없다`() {
        // when
        boxOffice.updateSeats(
            listOf(
                BookingSeat(1, 1, SeatClass.B),
                BookingSeat(2, 3, SeatClass.S),
                BookingSeat(3, 3, SeatClass.S),
                BookingSeat(4, 3, SeatClass.A),
            ),
        )

        // then
        assertEquals(false, boxOffice.isSubmitAvailable)
    }

    @Test
    fun `인원수_이하의_인원만큼_좌석_추가_시_올바른_예약_데이터를_보유한다`() {
        // when
        val result =
            boxOffice.updateSeats(
                listOf(
                    BookingSeat(1, 1, SeatClass.B),
                    BookingSeat(2, 3, SeatClass.S),
                    BookingSeat(3, 3, SeatClass.S),
                    BookingSeat(4, 3, SeatClass.A),
                    BookingSeat(4, 1, SeatClass.A),
                ),
            )

        // then
        assertAll(
            { assertEquals(Result.Success::class.java, result::class.java) },
        )
    }

    @Test
    fun `인원수_초과의_인원만큼_좌석_추가_시_올바른_예약_데이터를_보유한다`() {
        // when
        val result =
            boxOffice.updateSeats(
                listOf(
                    BookingSeat(1, 1, SeatClass.B),
                    BookingSeat(2, 3, SeatClass.S),
                    BookingSeat(3, 3, SeatClass.S),
                    BookingSeat(4, 3, SeatClass.A),
                    BookingSeat(4, 1, SeatClass.A),
                    BookingSeat(2, 1, SeatClass.S),
                ),
            )

        // then
        assertAll(
            { assertEquals(Result.Error::class.java, result::class.java) },
        )
    }
}
