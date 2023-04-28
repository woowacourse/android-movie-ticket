package woowacourse.movie.ui

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.R
import woowacourse.movie.model.MovieAndAd
import woowacourse.movie.model.RunningTime
import woowacourse.movie.model.ViewingDate
import woowacourse.movie.model.ViewingTime
import woowacourse.movie.selection.SeatSelectActivity
import java.time.LocalDate
import java.time.LocalTime

class SeatSelectActivityUITest {
    private val movie: MovieAndAd.Movie = MovieAndAd.Movie(
        R.drawable.slamdunk,
        "더 퍼스트 슬램덩크 시즌",
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
            SeatSelectActivity::class.java
        ).apply {
            putExtra(woowacourse.movie.KEY_MOVIE, movie)
            putExtra(KEY_RESERVATION_COUNT, 2)
            putExtra(KEY_RESERVATION_DATE, ViewingDate(LocalDate.of(2023, 1, 5)))
            putExtra(KEY_RESERVATION_TIME, ViewingTime(LocalTime.of(10, 0)))
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectActivity>(intent)

    @Test
    fun 좌석_선택_화면에_들어가면_좌석_화면이_나온다() {
        onView(withId(R.id.seat_table)).check(matches(isDisplayed()))
    }

    @Test
    fun 좌석을_선택하면_좌석이_선택된다() {
        // Given
        val seat = onView(withText("A1"))

        // when
        seat.perform(click())

        // then
        seat.check(matches(isSelected()))
    }

    @Test
    fun 선택된_좌석을_재선택하면_좌석선택이_해제된다() {
        // Given
        val seat = onView(withText("A1"))
        seat.perform(click())

        // when
        seat.perform(click())

        // then
        seat.check(matches(isNotSelected()))
    }

    @Test
    fun 구매를_원하는_좌석만큼_좌석을_선택하면_예매_버튼이_활성화된다() {
        // Given
        val seat1 = onView(withText("A1"))
        val seat2 = onView(withText("A2"))

        // when
        seat1.perform(click())
        seat2.perform(click())

        // then
        onView(withId(R.id.select_confrim_btn)).check(matches(isEnabled()))
    }
}
