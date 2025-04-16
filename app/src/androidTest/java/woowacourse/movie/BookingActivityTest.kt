package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_POSTER
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RELEASE_DATE
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RUNNING_TIME
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
                putExtra(KEY_MOVIE_RELEASE_DATE, "2025.4.1 ~ 2025.4.15")
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
        onView(withId(R.id.tv_screening_period)).check(matches(withText("2025.4.1 ~ 2025.4.15")))
    }

    @DisplayName("전달 받은 상영 시간을 출력한다")
    @Test
    fun movieRunningTimeDisplayTest() {
        onView(withId(R.id.tv_running_time)).check(matches(withText("152분")))
    }
}
