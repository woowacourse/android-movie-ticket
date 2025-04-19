package woowacourse.movie

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.isA
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.Movie
import woowacourse.movie.view.movie.MoviesActivity

class MoviesActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MoviesActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @Test
    fun 상영_리스트가_화면에_표시된다() {
        onView(withId(R.id.lv_movies))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 상영_리스트_첫번째_영화가_화면에_보여야한다() {
        onData(isA(Movie::class.java))
            .inAdapterView(withId(R.id.lv_movies))
            .atPosition(0)
            .check(matches(isDisplayed()))
    }

    @Test
    fun 상영_리스트_첫번째_영화제목은_라라랜드이다() {
        onData(isA(Movie::class.java))
            .inAdapterView(withId(R.id.lv_movies))
            .atPosition(0)
            .onChildView(withId(R.id.tv_movie_title))
            .check(matches(withText("라라랜드")))
    }

    @After
    fun finish() {
        Intents.release()
    }
}
