package woowacourse.movie

import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import woowacourse.movie.view.movie.MoviesActivity

class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun finish() {
        Intents.release()
    }
}
