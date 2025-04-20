package woowacourse.movie.uiTest

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withResourceName
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.isA
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.movielist.MovieListActivity

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    @DisplayName("영화 목록은 보여야 한다")
    fun listOfMoviesIsDisplayed() {
        onView(withId(R.id.movie_listview))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("첫 번째 영화 제목은 '해리 포터와 마법사의 돌 1'이다")
    fun firstMovieTitleIsHarryPotter() {
        onData(isA(Movie::class.java))
            .inAdapterView(withId(R.id.movie_listview))
            .atPosition(0)
            .onChildView(withId(R.id.item_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌 1")))
    }

    @Test
    @DisplayName("첫 번째 영화 상영일은 '상영일: 2025.4.1 ~ 2025.4.25'이다")
    fun firstMovieDateIsApril1to25() {
        onData(isA(Movie::class.java))
            .inAdapterView(withId(R.id.movie_listview))
            .atPosition(0)
            .onChildView(withId(R.id.item_movie_date))
            .check(matches(withText("상영일: 2025.4.1 ~ 2025.4.25")))
    }

    @Test
    @DisplayName("첫 번째 영화 러닝타임은 '러닝타임: 152분'이다")
    fun firstMovieTimeIs152minute() {
        onData(isA(Movie::class.java))
            .inAdapterView(withId(R.id.movie_listview))
            .atPosition(0)
            .onChildView(withId(R.id.item_movie_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    @DisplayName("첫 번째 영화는 '지금 예매' 버튼을 가지고 있다")
    fun firstMovieHasReservationButton() {
        onData(isA(Movie::class.java))
            .inAdapterView(withId(R.id.movie_listview))
            .atPosition(0)
            .onChildView(withId(R.id.item_reserve_button))
            .check(matches(withText("지금 예매")))
    }
}
