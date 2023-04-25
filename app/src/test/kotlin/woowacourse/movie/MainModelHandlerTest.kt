package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import woowacourse.movie.model.main.MainModelHandler

class MainModelHandlerTest {
    @Test
    fun `20개의 데이터에 3개의 광고가 CYCLE 당 한 개씩 들어간다`() {
        // given: 20개의 데이터, 3개의 광고, CYCLE은 3이다.
        val data = (1..20).map { "데이터$it" }
        val ads = (1..3).map { "광고$it" }
        val expected = listOf(
            "데이터1", "데이터2", "데이터3", "광고1",
            "데이터4", "데이터5", "데이터6", "광고2",
            "데이터7", "데이터8", "데이터9", "광고3",
            "데이터10", "데이터11", "데이터12", "광고1",
            "데이터13", "데이터14", "데이터15", "광고2",
            "데이터16", "데이터17", "데이터18", "광고3",
            "데이터19", "데이터20",
        )

        // when
        val actual = MainModelHandler.mergeAdvertisement(data, ads)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `10개의 데이터에 1개의 광고가 CYCLE 당 한 개씩 들어간다`() {
        // given: 20개의 데이터, 3개의 광고, CYCLE은 3이다.
        val data = (1..10).map { "데이터$it" }
        val ads = listOf("광고1")
        val expected = listOf(
            "데이터1", "데이터2", "데이터3", "광고1",
            "데이터4", "데이터5", "데이터6", "광고1",
            "데이터7", "데이터8", "데이터9", "광고1",
            "데이터10",
        )

        // when
        val actual = MainModelHandler.mergeAdvertisement(data, ads)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
