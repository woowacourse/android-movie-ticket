package woowacourse.movie.domain.screening

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MinuteTest {

    @Test
    fun `분을 음수로 생성하면 에러가 발생한다`() {
        assertThatIllegalArgumentException().isThrownBy {
            Minute(-1)
        }.withMessage("[ERROR] 분은 음수일 수 없습니다.")
    }
}
