package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_DAY
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_MONTH
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_YEAR
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_POSTER
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RELEASE_DATE
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RUNNING_TIME
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_DAY
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_MONTH
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_YEAR
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_TITLE
import woowacourse.movie.fixture.fakeContext

class BookingActivityTest {
    @Before
    fun setUp() {
        val intent =
            Intent(
                fakeContext,
                BookingActivity::class.java,
            ).apply {
                putExtra(KEY_MOVIE_TITLE, "해리 포터와 마법사의 돌")
                putExtra(KEY_MOVIE_POSTER, R.drawable.harry_potter_one)
                putExtra(KEY_MOVIE_START_YEAR, 2025)
                putExtra(KEY_MOVIE_START_MONTH, 4)
                putExtra(KEY_MOVIE_START_DAY, 1)
                putExtra(KEY_MOVIE_END_YEAR, 2025)
                putExtra(KEY_MOVIE_END_MONTH, 4)
                putExtra(KEY_MOVIE_END_DAY, 25)
                putExtra(KEY_MOVIE_RUNNING_TIME, "152분")
            }

        ActivityScenario.launch<BookingActivity>(intent)
    }

    @DisplayName("전달 받은 영화 이름을 출력한다")
    @Test
    fun movieTitleDisplayTest() {
        onView(withId(R.id.tv_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @DisplayName("전달 받은 상영일 출력한다")
    @Test
    fun movieReleaseDateDisplayTest() {
        onView(withId(R.id.tv_screening_period)).check(matches(withText("2025-04-01 ~ 2025-04-25")))
    }

    @DisplayName("전달 받은 상영 시간을 출력한다")
    @Test
    fun movieRunningTimeDisplayTest() {
        onView(withId(R.id.tv_running_time)).check(matches(withText("152분")))
    }

    @DisplayName("상영 날짜 스피너에 날짜 목록이 표시된다")
    @Test
    fun releasedDateSpinnerDisplayTest() {
        onView(withId(R.id.sp_date)).check(matches(isDisplayed()))
    }

    @DisplayName("예매 가능 시간 스피너에 시간 목록이 표시된다")
    @Test
    fun releasedTimeSpinnerDisplayTest() {
        onView(withId(R.id.sp_time)).check(matches(isDisplayed()))
    }
}
