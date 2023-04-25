import com.example.domain.model.price.MoviePrice
import junit.framework.TestCase
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class MoviePriceTest {

    @Test(expected = IllegalArgumentException::class)
    fun `영화 가격은 0원 이상이어야 한다`() {
        MoviePrice(-1)
    }

    @Test
    fun `무비데이와 조조가 겹치면 무비데이 할인이 선적용된 가격이 계산된다`() {
        val actual = MoviePrice().calculate(LocalDate.of(2023, 3, 10), LocalTime.of(10, 0))
        val expected = MoviePrice(9700)
        TestCase.assertEquals(actual, expected)
    }
}
