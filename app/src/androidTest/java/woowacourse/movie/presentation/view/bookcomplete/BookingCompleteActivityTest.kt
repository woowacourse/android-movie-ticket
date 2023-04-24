package woowacourse.movie.presentation.view.bookcomplete

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.IntentFixture
import woowacourse.movie.R
import java.time.LocalDate

class BookingCompleteActivityTest {
    private val intent =
        IntentFixture.getBookCompleteIntent(
            LocalDate.of(2023, 4, 23),
            LocalDate.of(2023, 5, 12),
            BookCompleteActivity::class.java
        )

    @get:Rule
    val activityRule = ActivityScenarioRule<BookCompleteActivity>(intent)

    @Test
    fun 영화_이름을_출력한다() {
        onView(withId(R.id.tv_book_movie_title)).check(matches(withText("해리포터 - 2편")))
    }

    @Test
    fun 영화_상영일을_출력한다() {
        onView(withId(R.id.tv_book_date)).check(matches(withText("2023.04.23 11:00")))
    }

    @Test
    fun 에약인원_정보를_출력한다() {
        onView(withId(R.id.tv_book_person_count)).check(matches(withText("일반 2명 | C1, C2")))
    }

    @Test
    fun 결제금액_정보를_출력한다() {
        onView(withId(R.id.tv_book_total_pay)).check(matches(withText("30000원 (현장 결제)")))
    }
}
