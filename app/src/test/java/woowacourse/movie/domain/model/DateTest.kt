package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.DateTimeException


class DateTest {
    @Test
    fun `날짜를 생성할 수 있다`() {
        val date = Date.from("2024.01.01")
        
        assertThat(date.toString()).isEqualTo("2024. 01. 01")
    }
    
    @ParameterizedTest
    @ValueSource(strings = ["2024.01", "202024.01.31", "20240103"])
    fun `날짜 생성 시 잘못된 형식을 입력하면 예외를 던진다`(dateString: String) {
        assertThrows<IllegalArgumentException> { Date.from(dateString) }
    }
    
    @ParameterizedTest
    @ValueSource(strings = ["100.01.01", "-1.01.01"])
    fun `날짜 생성 시 잘못된 연도를 입력하면 예외를 던진다`(dateString: String) {
        assertThrows<IllegalArgumentException> { Date.from(dateString) }
    }
    
    @ParameterizedTest
    @ValueSource(strings = ["2024.02.30"])
    fun `날짜 생성 시 없는 날짜를 입력하면 예외를 던진다`(dateString: String) {
        assertThrows<DateTimeException> { Date.from(dateString) }
    }
    
    
    @ParameterizedTest
    @ValueSource(strings = ["2024.00.01", "2024.13.01", "2024.-1.01", "2024.99.01"])
    fun `날짜 생성 시 잘못된 월을 입력하면 예외를 던진다`(dateString: String) {
        assertThrows<DateTimeException> { Date.from(dateString) }
    }
    
    @ParameterizedTest
    @ValueSource(strings = ["2024.01.00", "2024.01.32", "2024.01.-1", "2024.01.99"])
    fun `날짜 생성 시 잘못된 일을 입력하면 예외를 던진다`(dateString: String) {
        assertThrows<DateTimeException> { Date.from(dateString) }
    }
}
