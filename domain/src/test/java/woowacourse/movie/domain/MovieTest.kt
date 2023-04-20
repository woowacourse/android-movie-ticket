package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieTest {
    @Test
    fun `상영 시작, 끝 날짜 사이의 날짜 리스트를 가져온다`() {
        val movie = Movie(
            "기생충",
            LocalDate.of(2023, 9, 12),
            LocalDate.of(2023, 9, 15),
            131,
            "직업도 없이 허름한 반지하에 사는 기택 가족에게 돈을 벌 기회가 찾아온다. 친구의 소개로 부잣집 딸 다혜의 과외 선생님을 맡게 된 기택의 아들, 기우는 기대감에 부푼 채 글로벌 IT기업을 이끄는 박 사장의 저택에 들어간다."
        )

        val expected = listOf(
            LocalDate.of(2023, 9, 12),
            LocalDate.of(2023, 9, 13),
            LocalDate.of(2023, 9, 14),
            LocalDate.of(2023, 9, 15)
        )

        assertEquals(expected, movie.getDatesBetweenTwoDates())
    }
}
