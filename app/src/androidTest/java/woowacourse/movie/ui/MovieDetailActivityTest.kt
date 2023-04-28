package woowacourse.movie.ui

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
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.R
import woowacourse.movie.main.MainActivity
import woowacourse.movie.model.MovieAndAd
import woowacourse.movie.model.RunningTime
import woowacourse.movie.model.ViewingDate
import woowacourse.movie.model.ViewingTime
import woowacourse.movie.reservation.MovieDetailActivity
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailActivityTest {
    private val movie: MovieAndAd.Movie = MovieAndAd.Movie(
        R.drawable.slamdunk,
        "더 퍼스트 슬램덩크 시즌3",
        LocalDate.of(2023, 1, 4),
        LocalDate.of(2023, 2, 23),
        RunningTime(124),
        "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다." +
            "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다." +
            "북산고 농구부는 전국 대회에 출전해 라이벌 산왕공고와 맞붙는다. 멤버 각자가 쌓아온 성과, 그들이 짊어진 과거, 다양한 생각들이 뜨거운 코트 위에서 다시 한번 격렬하게 충돌한다."
    )

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieDetailActivity::class.java
        ).apply {
            putExtra(woowacourse.movie.KEY_MOVIE, movie)
            putExtra(KEY_RESERVATION_COUNT, 2)
            putExtra(KEY_RESERVATION_DATE, ViewingDate(LocalDate.of(2023, 1, 5)))
            putExtra(KEY_RESERVATION_TIME, ViewingTime(LocalTime.of(10, 0)))
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MainActivity>(intent)

    @Test
    fun 선택한_영화의_제목이_잘_나온다() {
        onView(withId(R.id.detail_title)).check(matches(withText("더 퍼스트 슬램덩크 시즌3")))
    }

    @Test
    fun 플러스_버튼을_누르면_예매_할_티켓의_수가_증가한다() {
        // given
        // when
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.plus)).perform(click())

        // then
        onView(withId(R.id.count)).check(matches(withText("3")))
    }

    @Test
    fun 마이너스_버튼을_누르면_예매_할_티켓의_수가_감소한다() {
        // given
        // when
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.minus)).perform(click())

        // then
        onView(withId(R.id.count)).check(matches(withText("2")))
    }
}
