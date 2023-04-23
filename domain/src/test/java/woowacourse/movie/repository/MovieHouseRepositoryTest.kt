package woowacourse.movie.repository

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MovieHouseRepositoryTest {

    @Test
    fun `영화관 저장소가 생성되면 아이디가 1인 샘플 데이터가 추가된다`() {
        val movieHouse = MovieHouseRepository.findById(1)

        assertThat(movieHouse).isNotNull
    }
}
