package woowacourse.movie.db.advertisement

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.matchers.shouldBe
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AdvertisementDaoTest {
    private val dao = AdvertisementDao()
    private val advertisements = AdvertisementDatabase.advertisements

    @Test
    fun `광고_데이터베이스의_모든_광고를_가지고_온다`() {
        val actual = dao.findAll().size
        val expected = advertisements.size
        actual shouldBe expected
    }
}
