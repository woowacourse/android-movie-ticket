package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.model.toPresentation
import java.time.LocalDate

class MovieDetailActivityTest {

    private val movieModel = Movie.of(
        "해리포터와 마법사의 돌 1",
        LocalDate.of(2023, 3, 1),
        LocalDate.of(2023, 3, 31),
        152,
        "해리포터 첫 번째 시리즈"
    ).toPresentation(R.drawable.img)

    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.MOVIE_KEY, movieModel)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieDetailActivity>(intent)

    @Test
    fun 제목을_띄운다() {
        onView(withId(R.id.text_title)).check(matches(withText("해리포터와 마법사의 돌 1")))
    }

    @Test
    fun 상영일을_띄운다() {
        onView(withId(R.id.text_playing_date)).check(matches(withText("상영일: 2023.3.1 ~ 2023.3.31")))
    }

    @Test
    fun 설명을_띄운다() {
        onView(withId(R.id.text_description)).check(matches(withText("해리포터 첫 번째 시리즈")))
    }

    @Test
    fun 플러스_버튼을_클릭하면_매수가_올라간다() {
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.text_count)).check(matches(withText("2")))
    }

    @Test
    fun 마이너스_버튼을_클릭하면_매수가_내려간다() {
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_minus)).perform(click())
        onView(withId(R.id.text_count)).check(matches(withText("2")))
    }
}
