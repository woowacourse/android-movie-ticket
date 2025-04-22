package woowacourse.movie.model

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HeadCountTest {
    private lateinit var headCount: HeadCount

    @BeforeEach
    fun setUp() {
        headCount = HeadCount()
    }

    @Test
    fun `인원 수의 초기값은 1이다`() {
        // Then
        headCount.value shouldBe 1
    }

    @Test
    fun `1 미만의 값이 저장될 경우 인원 수는 1이 된다`() {
        // Given
        val wrongHeadCount = HeadCount(0)

        // Then
        wrongHeadCount.value shouldBe 1
    }

    @Test
    fun `increase()를 호출하면 인원 수가 1 증가한다`() {
        // When
        headCount.increase()

        // Then
        headCount.value shouldBe 2
    }

    @Test
    fun `인원 수가 1보다 클 때 decrease()를 호출하면 인원 수가 1 감소한다`() {
        // Given
        headCount.increase()

        // When
        headCount.decrease()

        // Then
        headCount.value shouldBe 1
    }

    @Test
    fun `인원 수가 1 이하일 때 decrease()를 호출하면 인원 수가 감소하지 않는다`() {
        // When
        headCount.decrease()

        // Then
        headCount.value shouldBe 1
    }
}