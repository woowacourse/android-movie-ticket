import com.example.domain.model.model.Rank
import com.example.domain.model.price.MoviePrice
import org.junit.Assert.assertEquals
import org.junit.Test

class RankTest {

    @Test
    fun `Rank는 등급에 해당하는 금액으르 가진다`() {
        val actual: MoviePrice = Rank.A.price
        assertEquals(actual, MoviePrice(12000))
    }
}
