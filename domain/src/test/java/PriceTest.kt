import com.example.domain.model.price.Price
import org.junit.Test

class PriceTest {

    @Test(expected = IllegalArgumentException::class)
    fun `영화 가격은 0원 이상이어야 한다`() {
        Price(-1)
    }
}
