package domain.movie

import org.junit.Test

class RunningTimeTest {

    @Test
    fun `러닝타임은_1분_이상이다`() {
        RunningTime(1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `러닝타임이_1분보다_짧다면_예외가_발생한다`() {
        RunningTime(0)
    }
}
