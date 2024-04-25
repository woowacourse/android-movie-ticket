package woowacourse.movie.feature.home

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.child
import woowacourse.movie.feature.equalText
import woowacourse.movie.feature.firstMovie
import woowacourse.movie.feature.firstMovieItem
import woowacourse.movie.feature.runningTimeMessage
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RunWith(AndroidJUnit4::class)
class MovieHomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieHomeActivity::class.java)

    @Test
    fun `영화_목록_첫_번째_항목의_영화_제목이_보여진다`() {
        firstMovieItem.child(R.id.title_text)
            .equalText(firstMovie.title)
    }

    @Test
    fun `영화_목록_첫_번째_항목의_상영일이_보여진다`() {
        firstMovieItem.child(R.id.screening_date_text)
            .equalText(
                screeningDateRangeMessage(
                    firstMovie.startScreeningDate,
                    firstMovie.endScreeningDate,
                ),
            )
    }

    @Test
    fun `영화_목록_첫_번째_항목의_러닝타임이_보여진다`() {
        firstMovieItem.child(R.id.running_time_text)
            .equalText(firstMovie.runningTimeMessage())
    }

    private fun screeningDateRangeMessage(
        startScreeningDate: LocalDate,
        endScreeningDate: LocalDate,
    ): String {
        return "상영일: %s ~ %s".format(
            startScreeningDate.format(DateTimeFormatter.ofPattern("yyyy.M.d")),
            endScreeningDate.format(DateTimeFormatter.ofPattern("yyyy.M.d")),
        )
    }
}
