package woowacourse.movie.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.HeadCount

class HeadCountTest {
    private lateinit var headCount: HeadCount

    @BeforeEach
    fun setUp() {
        headCount = HeadCount()
    }

    @Test
    fun `예매 수량의 초기 값은 1이다`() {
        headCount.getCount() shouldBe 1
    }

    @Test
    fun `예매 수량이 1 미만일 경우 예매 수량은 1로 변경된다`() {
        headCount = HeadCount(-1)

        headCount.getCount() shouldBe 1
    }

    @Test
    fun `increase를 여러 번 호출하면 예매 수량이 그만큼 증가한다`() {
        repeat(3) { headCount.increase() }

        headCount.getCount() shouldBe 4
    }

    @Test
    fun `예매 수량은 1 미만으로 감소할 수 없다`() {
        repeat(3) { headCount.decrease() }

        headCount.getCount() shouldBe 1
    }

    @Test
    fun `decrease를 여러 번 호출하면 예매 수량이 그만큼 감소한다`() {
        headCount = HeadCount(4)

        repeat(2) { headCount.decrease() }
        headCount.getCount() shouldBe 2
    }
}
