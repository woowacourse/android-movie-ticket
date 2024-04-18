package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MovieTicketTest {
    private lateinit var movieTicket: MovieTicket
    
    @BeforeEach
    fun setUp() {
        movieTicket = MovieTicket(TITLE, SCREENING_DATE)
    }
    
    @Test
    fun `예매할 영화의 정보를 가지고 있다`() {
        assertThat(movieTicket.movieTitle).isEqualTo(TITLE)
        assertThat(movieTicket.screeningDate).isEqualTo(SCREENING_DATE)
    }
    
    @Test
    fun `예매할 티켓의 초기 인원 수는 1명이다` () {
        assertThat(movieTicket.count).isEqualTo(MovieTicket.MIN_RESERVATION_COUNT)
    }
    
    @Test
    fun `예매할 티켓의 인원 수를 증가시킨다`() {
        // when
        movieTicket.plusCount()
        
        // then
        assertThat(movieTicket.count).isEqualTo(MovieTicket.MIN_RESERVATION_COUNT + 1)
    }
    
    @Test
    fun `예매할 티켓의 인원 수는 20명을 초과할 수 없다` () {
        // when
        repeat(MovieTicket.MAX_RESERVATION_COUNT) {
            movieTicket.plusCount()
        }
        movieTicket.plusCount()
        
        // then
        assertThat(movieTicket.count).isEqualTo(MovieTicket.MAX_RESERVATION_COUNT)
    }
    
    @Test
    fun `예매할 티켓의 인원 수를 감소시킨다`() {
        // when
        movieTicket.minusCount()
        
        // then
        assertThat(movieTicket.count).isEqualTo(MovieTicket.MIN_RESERVATION_COUNT)
    }
    
    @Test
    fun `예매할 티켓의 인원 수는 1명 미만이 될 수 없다` () {
        // when
        movieTicket.minusCount()
        
        // then
        assertThat(movieTicket.count).isEqualTo(MovieTicket.MIN_RESERVATION_COUNT)
    }
    
    @ParameterizedTest
    @CsvSource(
        "1, 26000",
        "2, 39000",
        "3, 52000",
        "4, 65000",
        "5, 78000",
    )
    fun `추가한 인원 수에 따라 티켓의 금액이 결정된다`(
        additionalCount: Int,
        expectedPrice: Int,
    ) {
        // given
        repeat(additionalCount) {
            movieTicket.plusCount()
        }
        
        // when
        val totalPrice = movieTicket.totalPrice()
        
        // then
        assertThat(totalPrice).isEqualTo(expectedPrice)
    }
}
