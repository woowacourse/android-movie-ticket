package woowacourse.movie.feature.home

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.feature.checkViewHolderType
import woowacourse.movie.feature.child
import woowacourse.movie.feature.equalTextOnRecyclerViewItem
import woowacourse.movie.feature.firstMovie
import woowacourse.movie.feature.home.list.viewholder.MovieAdvertisementViewHolder
import woowacourse.movie.feature.home.list.viewholder.MovieViewHolder
import woowacourse.movie.feature.view
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RunWith(AndroidJUnit4::class)
class MovieHomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieHomeActivity::class.java)

    @Test
    fun `영화_목록이_보인다`() {
        view(R.id.movie_list)
            .check(matches(isDisplayed()))
    }

    @Test
    fun `영화_목록_첫_번째_항목의_영화_제목이_보인다`() {
        view(R.id.movie_list)
            .child(0)
            .equalTextOnRecyclerViewItem(firstMovie.title)
    }

    @Test
    fun `영화_목록_첫_번째_항목의_상영_기간이_보인다`() {
        val screeningDateRangeMessage =
            "상영일: %s ~ %s".format(
                firstMovie.startScreeningDate.message(),
                firstMovie.endScreeningDate.message(),
            )
        view(R.id.movie_list)
            .child(0)
            .equalTextOnRecyclerViewItem(screeningDateRangeMessage)
    }

    @Test
    fun `영화_목록_첫_번째_항목의_러닝_타임이_보인다`() {
        view(R.id.movie_list)
            .child(0)
            .equalTextOnRecyclerViewItem("러닝타임: %d분".format(firstMovie.runningTime))
    }

    @Test
    fun `영화_목록_첫_번째_항목은_영화_정보다`() {
        view(R.id.movie_list)
            .checkViewHolderType(0, MovieViewHolder::class.java)
    }

    @Test
    fun `영화_목록_네_번째_항목은_광고다`() {
        view(R.id.movie_list)
            .checkViewHolderType(3, MovieAdvertisementViewHolder::class.java)
    }

    private fun LocalDate.message() = format(DateTimeFormatter.ofPattern("yyyy.M.d"))
}
