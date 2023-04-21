package woowacourse.movie

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.ui.activity.MainActivity

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun 네_번째_요소를_누르면_우아한테크코스_사이트로_이동한다() {
        val position = 3 // 0 부터 시작
        onView(withId(R.id.main_movie_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, click()))

        intended(hasAction(Intent.ACTION_VIEW))
        intended(hasData(Uri.parse("https://woowacourse.github.io/")))
    }
}
