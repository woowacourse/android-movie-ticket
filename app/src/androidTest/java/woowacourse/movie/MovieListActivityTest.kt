package woowacourse.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.model.screening.Movie
import java.time.LocalDate

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun `예매_가능한_영화리스트를_보여준다`() {
        // given
        val movies =
            listOf(
                Movie(
                    id = 0,
                    title = "해리 포터와 마법사의 돌",
                    thumbnailResourceId = R.drawable.movie1,
                    startDate = LocalDate.of(2024, 3, 1),
                    endDate = LocalDate.of(2024, 3, 31),
                    runningTime = 152,
                    introduction =
                        """
                        《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                        """.trimIndent(),
                ),
            )
        val adapter =
            MovieAdapter(
                movies = movies,
                onTicketingButtonClick = {},
            )

        val movieItem = adapter.getItem(0) as Movie
        assertAll(
            { assertEquals(1, adapter.count) },
            { assertEquals("해리 포터와 마법사의 돌", movieItem.title) },
            { assertEquals(LocalDate.of(2024, 3, 1), movieItem.startDate) },
            { assertEquals(LocalDate.of(2024, 3, 31), movieItem.endDate) },
            { assertEquals(152, movieItem.runningTime) },
            {
                assertEquals(
                    """
                    《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                    """.trimIndent(),
                    movieItem.introduction,
                )
            },
        )
    }

    @Test
    fun `버튼을_누르면_해당_영화의_예매_화면으로_이동한다`() {
        onView(withId(R.id.btn_ticketing)).perform(click())
        onView(withId(R.id.cl_ticketing_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun `예매_화면에서_뒤로가기_버튼을_누르면_영화_목록_화면으로_이동한다`() {
        onView(withId(R.id.btn_ticketing)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.cl_movie_list_activity)).check(matches(isDisplayed()))
    }

    @Test
    fun `예매_결과_화면에서_뒤로가기_버튼을_누르면_영화_목록_화면으로_이동한다`() {
        onView(withId(R.id.btn_ticketing)).perform(click())
        onView(withId(R.id.btn_complete)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.cl_movie_list_activity)).check(matches(isDisplayed()))
    }
}
