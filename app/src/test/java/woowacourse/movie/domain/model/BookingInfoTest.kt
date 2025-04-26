package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.MOVIE_01
import java.time.LocalTime

class BookingInfoTest {
    private lateinit var movie: Movie
    private lateinit var bookingInfo: BookingInfo

    @BeforeEach
    fun setup() {
        movie = MOVIE_01.copy()
        bookingInfo =
            BookingInfo(
                movie =
                    Movie(
                        title = "해리 포터와 마법사의 돌",
                        startDate = MovieDate(2025, 4, 1),
                        endDate = MovieDate(2025, 4, 25),
                        runningTime = 152,
                    ),
                date = MovieDate(2025, 4, 1),
                time = MovieTime(9, 0),
                seats = MovieSeats(),
                ticketCount = TicketCount(),
            )
    }

    @Test
    fun `초기 날짜는 영화 시작일로 설정된다`() {
        // given & when & then
        assertThat(bookingInfo.selectedDate).isEqualTo(movie.startDate)
    }

    @Test
    fun `updateDate 호출 시 날짜와 상영시간이 변경된다`() {
        // given
        val expectedDate = MovieDate(2025, 4, 10)
        val expectedTime = MovieTime.getMovieTimes(DateType.from(expectedDate)).first()

        // when
        bookingInfo.updateDate(expectedDate)

        // then
        assertThat(bookingInfo.selectedDate).isEqualTo(expectedDate)
        assertThat(bookingInfo.selectedTime).isEqualTo(expectedTime)
    }

    @Test
    fun `updateMovieTime 호출 시 상영 시간이 변경된다`() {
        // given
        val expected = MovieTime(LocalTime.of(12, 0))

        // when
        bookingInfo.updateMovieTime(expected)

        // then
        assertThat(bookingInfo.selectedTime).isEqualTo(expected)
    }

    @Test
    fun `구매한 티켓 좌석 종류에 맞게 금액을 반환한다`() {
        // given & when
        bookingInfo.increaseTicketCount(2)

        // when
        bookingInfo.addSeat(MovieSeat(1, 1))
        bookingInfo.addSeat(MovieSeat(3, 1))
        bookingInfo.addSeat(MovieSeat(5, 1))

        // then
        assertThat(bookingInfo.totalPrice).isEqualTo(TicketPrice(10_000 + 12_000 + 15_000))
    }

    @Test
    fun `티켓 장수보다 많은 좌석을 선택할 수 없다`() {
        // given
        bookingInfo.increaseTicketCount()

        // when
        bookingInfo.addSeat(MovieSeat(1, 1))
        bookingInfo.addSeat(MovieSeat(1, 2))
        bookingInfo.addSeat(MovieSeat(1, 3))

        // then
        assertThat(bookingInfo.selectedSeats).hasSize(2)
    }
}
