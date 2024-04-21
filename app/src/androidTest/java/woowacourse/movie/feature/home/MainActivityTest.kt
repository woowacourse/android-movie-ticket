package woowacourse.movie.feature.home

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.model.data.dto.MovieContent

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private val firstMovieContent = MovieContentsImpl.findAll().first()

    @Test
    fun `영화_목록_첫_번째_항목의_영화_제목이_보여진다`() {
        val firstMovieContentItem =
            onData(anything()).inAdapterView(withId(R.id.movie_content_list)).atPosition(0)
        firstMovieContentItem.onChildView(withId(R.id.title_text))
            .check(matches(withText(firstMovieContent.title)))
    }

    @Test
    fun `영화_목록_첫_번째_항목의_상영일이_보여진다`() {
        val firstMovieContentItem =
            onData(anything()).inAdapterView(withId(R.id.movie_content_list)).atPosition(0)
        firstMovieContentItem.onChildView(withId(R.id.screening_date_text))
            .check(matches(withText(firstMovieContent.screeningDateMessage())))
    }

    @Test
    fun `영화_목록_첫_번째_항목의_러닝타임이_보여진다`() {
        val firstMovieContentItem =
            onData(anything()).inAdapterView(withId(R.id.movie_content_list)).atPosition(0)
        firstMovieContentItem.onChildView(withId(R.id.running_time_text))
            .check(matches(withText(firstMovieContent.runningTimeMessage())))
    }

    private fun MovieContent.screeningDateMessage(): String {
        return screeningDate.run { "상영일: %d.%d.%d".format(year, month, day) }
    }

    private fun MovieContent.runningTimeMessage(): String {
        return "러닝타임: %d분".format(runningTime)
    }
}
