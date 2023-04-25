package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.domain.Movie
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.view.moviedetail.MovieDetailActivity
import woowacourse.movie.view.movielist.MovieListActivity
import woowacourse.movie.view.viewmodel.toUIModel
import java.time.LocalDate

class MovieDetailActivityTest {

    private val movie: Movie = Movie(
        "테스트 제목",
        startDate = LocalDate.of(2023, 4, 1),
        endDate = LocalDate.of(2023, 4, 30),
        100,
        "테스트",
        R.drawable.img,
    )

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieDetailActivity::class.java
        ).apply {
            putExtra("movie", movie.toUIModel())
        }

    @get:Rule
    internal val activityRule = ActivityScenarioRule<MovieListActivity>(intent)

    @Test
    fun 주말인_날짜를_클릭하면_시간이_10시부터_시작한다() {
        // given: 앱 실행 시 영화 정보들이 나오고

        // when : 사용자가 스피너를 클릭해 주말 날짜를 클릭하면
        onView(withId(R.id.select_date)).perform(click())
        onView(withText("2023-04-01")).perform(click())

        // then: 시간 스피너가 9시로 바뀐다.
        onView(withId(R.id.select_time))
            .check(matches(withSpinnerText("09:00")))
    }

    @Test
    fun 플러스버튼을_클릭하면_선택하는_인원수가_늘어난다() {
        // given: 앱 실행 시 영화 정보들이 나오고

        // when : 플러스 버튼을 클릭하면
        onView(withId(R.id.plus_people)).perform(click())

        // then: 인원수가 2가 된다
        onView(withId(R.id.number_of_people))
            .check(matches(withText("2")))
    }

    @Test
    fun 마이너스버튼을_클릭하면_선택하는_인원수가_줄어든다() {
        // given: 앱 실행 시 영화 정보들이 나오고

        // when : 플러스 버튼을 두 번 클릭했다가 마이너스 버튼을 클릭하면
        onView(withId(R.id.plus_people)).perform(click())
        onView(withId(R.id.plus_people)).perform(click())
        onView(withId(R.id.minus_people)).perform(click())

        // then: 인원수가 2가 된다
        onView(withId(R.id.number_of_people))
            .check(matches(withText("2")))
    }

    @Test
    fun 인원수가_1인_상태에서_마이너스버튼을_클릭하면_인원수는_그대로_1이다() {
        // given: 앱 실행 시 영화 정보들이 나오고

        // when : 마이너스 버튼을 클릭하면
        onView(withId(R.id.minus_people)).perform(click())

        // then: 인원수는 그대로 1이다
        onView(withId(R.id.number_of_people))
            .check(matches(withText("1")))
    }

    @Test
    fun 인원수가_10인_상태에서_플러스버튼을_클릭하면_인원수는_그대로_10이다() {
        // given: 앱 실행 시 영화 정보들이 나오고

        // when : 플러스 버튼을 9번 눌러 인원수를 10으로 만든 후 다시 플러스 버튼을 누르면
        for (i in 1..9)
            onView(withId(R.id.plus_people)).perform(click())

        // then: 인원수는 그대로 10이다
        onView(withId(R.id.number_of_people))
            .check(matches(withText("10")))
    }
}
