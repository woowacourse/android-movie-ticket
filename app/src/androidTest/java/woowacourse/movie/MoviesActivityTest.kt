package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.MoviesActivity
import woowacourse.movie.activity.MovieReservationActivity

@RunWith(AndroidJUnit4::class)
class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Test
    fun clickButton_navigateToOtherActivity() {
        Intents.init()
        // when : 영화 목록에 있는 버튼을 눌렀을 때,
        onView(withId(R.id.item_movie_reservation_button)).perform(click())
        // then : 영화 예약 화면으로 넘어간다
        intended(hasComponent(MovieReservationActivity::class.java.name))
        Intents.release()
    }
}
