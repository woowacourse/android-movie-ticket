package woowacourse.movie.ticket

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.ticket.HeadCount

class HeadCountTest {
    private lateinit var headCount: HeadCount

    @BeforeEach
    fun setUp() {
        headCount = HeadCount()
    }

    @Test
    fun `예매 수량의 초기 값은 1이다`() {
        headCount.value shouldBe 1
    }

    @Test
    fun `예매 수량은 1 미만일 수 없다`() {
        assertThrows<IllegalArgumentException> { HeadCount(-1) }
    }

    @Test
    fun `increase를 호출하면 예매 수량이 증가한다`() {
        repeat(3) { headCount += 1 }

        headCount.value shouldBe 4
    }

    @Test
    fun `decrease를 여러 번 호출하면 예매 수량이 그만큼 감소한다`() {
        headCount = HeadCount(4)

        repeat(2) { headCount -= 1 }
        headCount.value shouldBe 2
    }
}
