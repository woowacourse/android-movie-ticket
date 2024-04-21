package woowacourse.movie.feature.home

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.child
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.firstMovieContent
import woowacourse.movie.feature.firstMovieContentItem
import woowacourse.movie.feature.runningTimeMessage
import woowacourse.movie.feature.screeningDateMessage

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `영화_목록_첫_번째_항목의_영화_제목이_보여진다`() {
        firstMovieContentItem.child(R.id.title_text)
            .equalText(firstMovieContent.title)
    }

    @Test
    fun `영화_목록_첫_번째_항목의_상영일이_보여진다`() {
        firstMovieContentItem.child(R.id.screening_date_text)
            .equalText(firstMovieContent.screeningDateMessage())
    }

    @Test
    fun `영화_목록_첫_번째_항목의_러닝타임이_보여진다`() {
        firstMovieContentItem.child(R.id.running_time_text)
            .equalText(firstMovieContent.runningTimeMessage())
    }
}
