package woowacourse.movie

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.data.screening.Movies
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.view.screening.ScreeningActivity
import java.time.LocalDate

class ScreeningActivityTest {
    private val harryPotter = Movies().harryPotterPhilosopersStone

    @get:Rule
    val activityRule =
        ActivityScenarioRule<ScreeningActivity>(
            ScreeningActivity.testIntent(
                ApplicationProvider.getApplicationContext(),
                arrayOf(
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 4, 1),
                        LocalDate.of(2025, 4, 25),
                    ),
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 25),
                    ),
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 25),
                    ),
                ),
            ),
        )

    @Test
    fun `상영_리스트가_표시된다`() {
        onView(withId(R.id.lv_screening_movies))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `상영_리스트에는_각각의_상영_정보가_표시된디`() {
        onData(
            allOf(
                `is`(instanceOf(Screening::class.java)),
                `is`(
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 25),
                    ),
                ),
            ),
        ).check(matches(isDisplayed()))
    }

    @Test
    fun `상영_정보에는_영화_제목이_표시된다`() {
        onData(
            allOf(
                `is`(instanceOf(Screening::class.java)),
                `is`(
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 25),
                    ),
                ),
            ),
        ).onChildView(withId(R.id.tv_item_screening_title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `상영_정보에는_상영일이_표시된다`() {
        onData(
            allOf(
                `is`(instanceOf(Screening::class.java)),
                `is`(
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 25),
                    ),
                ),
            ),
        ).onChildView(withId(R.id.tv_item_screening_date))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `상영_정보에는_포스터가_표시된다`() {
        onData(
            allOf(
                `is`(instanceOf(Screening::class.java)),
                `is`(
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 25),
                    ),
                ),
            ),
        ).onChildView(withId(R.id.iv_item_screening_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `상영_정보에는_러닝타임이_표시된다`() {
        onData(
            allOf(
                `is`(instanceOf(Screening::class.java)),
                `is`(
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 25),
                    ),
                ),
            ),
        ).onChildView(withId(R.id.tv_item_screening_running_time))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `지금_예매를_클릭하면_영화_예매_완료_화면이_보여진다`() {
        onData(
            allOf(
                `is`(instanceOf(Screening::class.java)),
                `is`(
                    Screening(
                        harryPotter,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 25),
                    ),
                ),
            ),
        ).onChildView(withId(R.id.btn_item_screening_reserve))
            .perform(click())

        onView(withId(R.id.layout_reservation))
            .check(matches(isDisplayed()))
    }
}
