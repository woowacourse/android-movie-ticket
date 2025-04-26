package woowacourse.movie.movies

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.view.movies.MoviesActivity
import java.time.LocalDate

class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @Test
    fun 영화의_지금_예매_버튼을_누르면_영화_예매_화면으로_영화의_정보를_넘긴다() {
        // given: 영화 목록 뷰에서
        // when: 지금 예매 버튼을 누르면
        Espresso
            .onView(ViewMatchers.withId(R.id.btn_movie_reservation))
            .perform(ViewActions.click())

        // then: 영화 예매 화면으로 영화의 정보를 넘긴다
        Intents.intended(
            IntentMatchers.hasExtra(
                "data",
                Movie(
                    1,
                    "라라랜드",
                    R.drawable.lalaland,
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 20),
                    120,
                ),
            ),
        )
    }

    @After
    fun finish() {
        Intents.release()
    }
}
