package woowacourse.movie.view

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.movie.R
import woowacourse.movie.adapter.ScreeningRecyclerViewAdapter
import woowacourse.movie.model.screening.DatePeriod
import woowacourse.movie.model.screening.Screening
import java.time.LocalDate

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun `예매_가능한_영화리스트를_보여준다`() {
        // given
        val screenings =
            listOf(
                Screening.of(
                    screeningId = 0,
                    movieId = 0,
                    datePeriod =
                        DatePeriod(
                            startDate = LocalDate.of(2024, 3, 1),
                            endDate = LocalDate.of(2024, 3, 31),
                            dateSpan = 1,
                        ),
                ),
            )
        val adapter =
            ScreeningRecyclerViewAdapter(
                screeningItems = screenings,
                advertisementDrawableId = R.drawable.screening_advertisement,
                ticketingButtonClickListener = {},
            )

        val screeningItem = screenings.first()
        assertAll(
            { assertEquals(1, adapter.itemCount) },
            { assertEquals("해리 포터와 마법사의 돌", screeningItem.movie.title) },
            { assertEquals(LocalDate.of(2024, 3, 1), screeningItem.datePeriod.startDate) },
            { assertEquals(LocalDate.of(2024, 3, 31), screeningItem.datePeriod.endDate) },
            { assertEquals(152, screeningItem.movie.runningTime) },
            {
                assertEquals(
                    """
                    《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                    """.trimIndent(),
                    screeningItem.movie.introduction,
                )
            },
        )
    }
}
