package woowacourse.movie.model
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.R

class TicketTest {
    private lateinit var uiMovie: UiMovie
    private lateinit var ticket: Ticket

    @BeforeEach
    fun setUp() {
        uiMovie =
            UiMovie(
                poster = R.drawable.poster,
                title = "해리 포터와 마법사의 돌",
                content = "마법 영화",
                "2024.3.1",
                runningTime = 152,
            )
        ticket = Ticket(uiMovie, 10)
    }

    @Test
    fun `올바른 제목을 반환하는지 확인`() {
        assertEquals("해리 포터와 마법사의 돌", ticket.getTitle())
    }

    @Test
    fun `올바른 개봉일을 반환하는지 확인`() {
        assertEquals("2024.3.1", ticket.getOpeningDay())
    }

    @Test
    fun `티켓 수량 증가 기능 검증`() {
        ticket.increaseQuantity()
        assertEquals(11, ticket.quantity)
    }

    @Test
    fun `티켓 수량 감소 기능 검증`() {
        ticket.decreaseQuantity()
        assertEquals(9, ticket.quantity)
    }

    @Test
    fun `티켓 수량 감소 시 최소 수량 이하로 떨어지지 않는지 검증`() {
        ticket = Ticket(uiMovie, Ticket.MINIMUM_QUANTITY)
        ticket.decreaseQuantity()
        assertEquals(Ticket.MINIMUM_QUANTITY, ticket.quantity)
    }

    @Test
    fun `올바른 가격을 반환하는지 확인`() {
        assertEquals(13_000, ticket.getPrice())
    }
}
