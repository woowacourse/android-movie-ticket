package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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

    lateinit var activityScenario: ActivityScenario<MovieDetailActivity>

    @Test
    fun 영화_정보를_띄운다() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_KEY, movieModel)
        activityScenario = ActivityScenario.launch(intent)
        onView(withId(R.id.text_title)).check(matches(withText("해리포터와 마법사의 돌 1")))
        onView(withId(R.id.text_playing_date)).check(matches(withText("상영일: 2023.3.1 ~ 2023.3.31")))
        onView(withId(R.id.text_description)).check(matches(withText("해리포터 첫 번째 시리즈")))
    }

    @Test
    fun 플러스_버튼을_클릭하면_매수가_올라간다() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_KEY, movieModel)
        activityScenario = ActivityScenario.launch(intent)
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.text_count)).check(matches(withText("2")))
    }

    @Test
    fun 마이너스_버튼을_클릭하면_매수가_내려간다() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_KEY, movieModel)
        activityScenario = ActivityScenario.launch(intent)
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_minus)).perform(click())
        onView(withId(R.id.text_count)).check(matches(withText("2")))
    }
}
