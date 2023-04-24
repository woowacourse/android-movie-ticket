package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.domain.model.model.Movie
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.mapper.toMovieModel
import java.time.LocalDate

class MovieDetailActivityTest {

    private val movieModel = Movie(
        R.drawable.movie_poster,
        "해리포터 2",
        LocalDate.of(2023, 3, 1),
        LocalDate.of(2023, 3, 31),
        152,
        "줄거리"
    ).toMovieModel()

    private val intent = Intent(
        ApplicationProvider.getApplicationContext(),
        MovieDetailActivity::class.java
    ).putExtra("movie", movieModel)

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieDetailActivity>(intent)

    @Test
    fun 스피너에서_주말을_선택하면_시간_스피너는_오전_9시부터_시작한다() {

        // given: 날짜 스피너를 열고
        onView(withId(R.id.spinner_date)).perform(click())

        // when: 스피너 아이템 중 주말을 선택하면
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2023, 3, 4))
            )
        ).perform(click())

        // then: 시간 스피너는 오전 9시부터 시작한다
        onView(withId(R.id.spinner_time)).check(matches(withSpinnerText("09:00")))
    }

    @Test
    fun 스피너에서_평일을_선택하면_시간_스피너는_오전_10시부터_시작한다() {

        // given: 날짜 스피너를 열고
        onView(withId(R.id.spinner_date)).perform(click())

        // when: 스피너 아이템 중 평일을 선택하면
        onData(
            allOf(`is`(instanceOf(LocalDate::class.java)), `is`(LocalDate.of(2023, 3, 3)))
        ).perform(click())

        // then: 시간 스피너는 오전 10시부터 시작한다
        onView(withId(R.id.spinner_time)).check(matches(withSpinnerText("10:00")))
    }
}
