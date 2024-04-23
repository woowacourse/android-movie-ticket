package woowacourse.movie

import androidx.test.espresso.Espresso.onData
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.hasProperty
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.Movie
import woowacourse.movie.presentation.movieList.MovieListActivity

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun `예매_가능한_영화리스트를_보여준다`() {
        onData(
            allOf(
                `is`(instanceOf(Movie::class.java)),
                hasProperty(
                    "title",
                    `is`("해리 포터와 마법사의 돌"),
                ),
                hasProperty(
                    "thumbnail",
                    `is`("https://github.com/kmkim2689/codetree-TILs/assets/101035437/c57f8b0c-5b15-418c-88db-15f2ada4cdcf"),
                ),
                hasProperty("date", `is`("2024.3.1")),
                hasProperty("runningTime", `is`(152)),
                hasProperty(
                    "introduction",
                    `is`(
                        """
                        《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                        """.trimIndent(),
                    ),
                ),
            ),
        )
    }
}
