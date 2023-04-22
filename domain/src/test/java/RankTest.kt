import com.example.domain.model.model.Rank
import org.junit.Assert.assertEquals
import org.junit.Test

class RankTest {

    @Test
    fun `0 또는 1 번째 열이면 B 등급이다`() {
        val actual: Rank = Rank.map(0)
        assertEquals(actual, Rank.B)
    }

    @Test
    fun `2 또는 3 번째 열이면 S 등급이다`() {
        val actual: Rank = Rank.map(2)
        assertEquals(actual, Rank.S)
    }

    @Test
    fun `4 번째 열이면 A 등급이다`() {
        val actual: Rank = Rank.map(4)
        assertEquals(actual, Rank.A)
    }
}
