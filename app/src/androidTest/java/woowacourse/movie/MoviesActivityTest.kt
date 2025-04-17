package woowacourse.movie

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.init
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.release
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.BookingDetailActivity.Companion.MOVIE_END_DATE_KEY
import woowacourse.movie.BookingDetailActivity.Companion.MOVIE_RUNNING_TIME_KEY
import woowacourse.movie.BookingDetailActivity.Companion.MOVIE_START_DATE_KEY
import woowacourse.movie.BookingDetailActivity.Companion.MOVIE_TITLE_KEY

@Suppress("ktlint:standard:function-naming")
class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Test
    fun 액티비티가_실행되면_영화_항목이_나타난다() {
        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 예매_버튼을_클릭하면_예매_화면으로_이동한다() {
        init()

        onData(anything())
            .inAdapterView(withId(R.id.lv_movies))
            .atPosition(0)
            .onChildView(withId(R.id.btn_movie_booking))
            .perform(click())

        intended(
            allOf(
                hasComponent(BookingDetailActivity::class.java.name),
                hasExtra(MOVIE_TITLE_KEY, "해리 포터와 마법사의 돌"),
                hasExtra(MOVIE_START_DATE_KEY, "2025-04-01"),
                hasExtra(MOVIE_END_DATE_KEY, "2025-04-25"),
                hasExtra(MOVIE_RUNNING_TIME_KEY, 152),
            ),
        )

        release()
    }
}
