package woowacourse.movie.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Movie
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

class MovieDaoTest {
    private lateinit var movieDao: MovieDao

    @BeforeEach
    fun setUp() {
        movieDao = MovieDao()
    }


    @Test
    fun ` movie_ticket의 데이터를 불러올 수 있다`() {
        val actual = movieDao.movies()
        val expected = mapOf(
            "해리포터와 마법사의 돌" to
            Movie(
                "해리포터와 마법사의 돌",
                "https://upload.wikimedia.org/wikipedia/ko/d/dd/%ED%95%B4%EB%A6%AC_%ED%8F%AC%ED%84%B0%EC[…]B%B2%95%EC%82%AC%EC%9D%98_%EB%8F%8C_%EC%98%81%ED%99%94.jpg",
                LocalDateTime.of(2025, 4, 1, 0, 0, 0),
                LocalDateTime.of(2025, 4, 25, 23, 59, 59),
                152.minutes)
        )
        assertThat(actual).isEqualTo(expected)

    }
}