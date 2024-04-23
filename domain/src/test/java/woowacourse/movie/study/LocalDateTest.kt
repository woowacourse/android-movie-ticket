package woowacourse.movie.study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class LocalDateTest {
    @Test
    @DisplayName("yyyy.mm.dd 를 LocalDate 로 parse")
    fun test() {
        // given
        val date = "2024.04.15"
        val expect = LocalDate.of(2024, 4, 15)
        // when
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val localDate = LocalDate.parse(date, formatter)
        // then
        localDate shouldBe expect
    }

    @Test
    @DisplayName("LocalDateTime 를 yyyy.mm.dd 로 format")
    fun changeToYyyyMmDd() {
        val localDate = LocalDate.of(2024, 4, 16)
        val pattern = "yyyy.MM.dd"
        val str = localDate.format(DateTimeFormatter.ofPattern(pattern))
        str shouldBe "2024.04.16"
    }

    @Test
    @DisplayName("2022.04.01 을 LocalDate 로 변환")
    fun test1() {
        // given
        val date = "2022.04.01"
        val timezone = "UTC"
        // when
//        val localDate = LocalDate.parse(date)
//        val localDateWithTimezone =
//            LocalDate.parse(date).atStartOfDay().toInstant(ZoneOffset.of(timezone)).toLocalDate()
        println(LocalDate.now())
        println(LocalDate.now(ZoneId.of("UTC")))
        println(LocalDate.now(ZoneOffset.of(ZoneOffset.UTC.id)))
    }
}
