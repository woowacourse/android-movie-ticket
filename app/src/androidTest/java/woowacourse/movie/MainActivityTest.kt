package woowacourse.movie

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.Movie
import java.time.LocalDate

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `리스트뷰가_화면에_보인다`() {
        onView(withId(R.id.listview_layout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `리스트뷰_영화이름이_존재하는지_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_title))
            .check(
                matches(
                    allOf(
                        withText("해리 포터와 마법사의 돌"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화상영일이_존재하는지_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_screening_date))
            .check(
                matches(
                    allOf(
                        withText("2025.4.1"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화상영시간이_존재하는지_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_running_time))
            .check(
                matches(
                    allOf(
                        withText("152분"),
                        isDisplayed(),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화예매_클릭후_데이터를_확인한다`() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_layout))
            .atPosition(0)
            .onChildView(withId(R.id.btn_reserve))
            .perform(click())

        val movie =
            Movie(
                imageSource = R.drawable.harry_potter,
                title = "해리 포터와 마법사의 돌",
                screeningStartDate = LocalDate.of(2025, 4, 1),
                screeningEndDate = LocalDate.of(2025, 4, 25),
                runningTime = 152,
            )

        intended(hasExtra("movieData", movie))
    }
}
