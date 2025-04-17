package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.Movie
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
        onView(withId(R.id.btn_movie_reservation))
            .perform(click())

        // then: 영화 예매 화면으로 영화의 정보를 넘긴다
        intended(
            hasExtra(
                "data",
                Movie(
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
