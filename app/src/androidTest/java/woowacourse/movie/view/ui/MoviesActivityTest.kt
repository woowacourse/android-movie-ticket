package woowacourse.movie.view.ui

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.ui.view.BookingActivity
import woowacourse.movie.ui.view.MoviesActivity

@RunWith(AndroidJUnit4::class)
class MoviesActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun 영화_제목이_표시된다() {
        val expected = listOf("승부", "미키 17")

        expected.forEachIndexed { index, title ->
            onData(anything())
                .inAdapterView(withId(R.id.listview_movies))
                .atPosition(index)
                .onChildView(withId(R.id.textview_title))
                .check(matches(withText(title)))
        }
    }

    @Test
    fun 상영일이_표시된다() {
        val expected = listOf(
            "상영일: 2025-03-26 ~ 2025-04-26",
            "상영일: 2025-04-01 ~ 2025-04-29"
        )

        expected.forEachIndexed { index, title ->
            onData(anything())
                .inAdapterView(withId(R.id.listview_movies))
                .atPosition(index)
                .onChildView(withId(R.id.textview_screeningdate))
                .check(matches(withText(title)))
        }
    }

    @Test
    fun 러닝타임이_표시된다() {
        val expected = listOf(
            "러닝타임: 115분",
            "러닝타임: 137분"
        )

        expected.forEachIndexed { index, title ->
            onData(anything())
                .inAdapterView(withId(R.id.listview_movies))
                .atPosition(index)
                .onChildView(withId(R.id.textview_runningtime))
                .check(matches(withText(title)))
        }
    }

    @Test
    fun 예매_버튼을_누르면_화면이_이동되고_영화_데이터가_전달된다() {
        onData(anything())
            .inAdapterView(withId(R.id.listview_movies))
            .atPosition(0)
            .onChildView(withId(R.id.button_book))
            .perform(click())

        intended(hasComponent(BookingActivity::class.java.name))

        intended(allOf(
            hasExtraWithKey("Movie")
        ))
    }
}