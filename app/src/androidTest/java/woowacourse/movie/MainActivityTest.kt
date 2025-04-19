package woowacourse.movie

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
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import woowacourse.movie.ui.view.booking.BookingActivity
import woowacourse.movie.ui.view.main.MainActivity

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @DisplayName("영화 제목이 표시된다")
    @Test
    fun movieTitleTest() {
        val expected = listOf("승부", "미키 17")

        expected.forEachIndexed { index, title ->
            onData(anything())
                .inAdapterView(withId(R.id.movies))
                .atPosition(index)
                .onChildView(withId(R.id.title))
                .check(matches(withText(title)))
        }
    }

    @DisplayName("상영일이 표시된다")
    @Test
    fun screeningDateTest() {
        val expected =
            listOf(
                "상영일: 2025-03-26 ~ 2025-04-26",
                "상영일: 2025-04-01 ~ 2025-04-29",
            )

        expected.forEachIndexed { index, title ->
            onData(anything())
                .inAdapterView(withId(R.id.movies))
                .atPosition(index)
                .onChildView(withId(R.id.screeningDate))
                .check(matches(withText(title)))
        }
    }

    @DisplayName("러닝타임이 표시된다")
    @Test
    fun runningTimeTest() {
        val expected =
            listOf(
                "러닝타임: 115분",
                "러닝타임: 137분",
            )

        expected.forEachIndexed { index, title ->
            onData(anything())
                .inAdapterView(withId(R.id.movies))
                .atPosition(index)
                .onChildView(withId(R.id.runningTime))
                .check(matches(withText(title)))
        }
    }

    @DisplayName("예매 버튼을 누르면 화면이 이동되고 영화 데이터가 전달된다")
    @Test
    fun intentTest() {
        onData(anything())
            .inAdapterView(withId(R.id.movies))
            .atPosition(0)
            .onChildView(withId(R.id.reservation))
            .perform(click())

        intended(hasComponent(BookingActivity::class.java.name))

        intended(
            allOf(
                hasExtraWithKey("Movie"),
            ),
        )
    }
}
