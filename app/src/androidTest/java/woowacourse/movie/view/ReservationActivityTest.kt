package woowacourse.movie.view

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.view.model.MovieListModel
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {

    private val movie = MovieListModel.MovieUiModel(
        "해리 포터와 마법사의 돌",
        LocalDate.of(2024, 3, 1),
        LocalDate.of(2024, 3, 31),
        152,
        R.drawable.harry_potter1_poster,
        "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다."
    )

    private val intent = ReservationActivity.newIntent(
        ApplicationProvider.getApplicationContext(),
        movie
    )

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun 영화_제목을_표시한다() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 처음_표시되는_인원수는_1이다() {
        onView(withId(R.id.people_count)).check(matches(withText("1")))
    }

    @Test
    fun 플러스_버튼을_한_번_클릭하면_인원수는_2이다() {
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.people_count)).check(matches(withText("2")))
    }

    @Test
    fun 초기_인원_1인_경우_마이너스_버튼을_눌러도_인원수는_1이다() {
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.people_count)).check(matches(withText("1")))
    }
}
