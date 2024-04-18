package woowacourse.movie.java

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.jupiter.api.Test
import woowacourse.movie.R

class MovieReservationCompleteTest {
//    @get:Rule
//    val activityRule = ActivityScenarioRule<MovieReservationCompleteTest>(Intent)

    @Test
    fun `티켓의_영화이름이_표시되어야_한다`() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_list_view))
            .check(ViewAssertions.matches(ViewMatchers.withText("해리 포터와 마법사의 돌")))
    }
}
