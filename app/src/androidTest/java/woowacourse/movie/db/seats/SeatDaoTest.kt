package woowacourse.movie.db.seats

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SeatDaoTest {
    private val dao = SeatsDao()
    private val seats = SeatsDatabase.seats

    @Test
    fun `좌석_데이터베이스의_모든_좌석을_가지고_온다`() {
        val actual = dao.findAll().size
        val expected = seats.size
        actual shouldBe expected
    }
}
