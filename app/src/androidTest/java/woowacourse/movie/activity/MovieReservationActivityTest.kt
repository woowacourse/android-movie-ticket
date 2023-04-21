package woowacourse.movie.activity

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matchers.hasToString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.DateRange
import woowacourse.movie.domain.Image
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.data.LocalFormattedDate
import woowacourse.movie.view.data.LocalFormattedTime
import woowacourse.movie.view.mapper.MovieMapper.toView
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class MovieReservationActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationActivity>(
        MovieReservationActivity.from(
            ApplicationProvider.getApplicationContext(),
            Movie(
                Image(0),
                "해리 포터",
                DateRange(
                    LocalDate.of(2024, 3, 1),
                    LocalDate.of(2024, 3, 31),
                ),
                153,
                "adsfasdfadsf",
            ).toView()
        )
    )

    @Test
    fun 평일인_2024년_3월_1일을_스피너에서_선택하면_10시부터_22시까지_2시간_간격으로_표시한다() {
        // given

        // when
        val date = "2024-03-01"
        onView(withId(R.id.movie_reservation_date_spinner)).perform(click())
        onData(
            allOf(
                instanceOf(LocalFormattedDate::class.java), hasToString(date)
            )
        ).perform(click())

        // then
        val times = listOf(
            "10:00",
            "12:00",
            "14:00",
            "16:00",
            "18:00",
            "20:00",
            "22:00",
        )

        for (index in times.indices) {
            onData(`is`(instanceOf(LocalFormattedTime::class.java))).inAdapterView(withId(R.id.movie_reservation_time_spinner))
                .atPosition(index).check(matches(withText(times[index])))
        }
    }

    @Test
    fun 카운터의_더하기_버튼을_누르면_카운트가_1_증가한다() {
        // given

        // when
        onView(withId(R.id.movie_reservation_people_count_plus)).perform(click())

        // then
        val count = 2
        onView(withId(R.id.movie_reservation_people_count)).check(matches(withText(count.toString())))
    }
    @Test
    fun 카운터의_빼기_버튼을_누르면_카운트가_1_감소한다() {
        // given
        onView(withId(R.id.movie_reservation_people_count_plus)).perform(click())

        // when
        onView(withId(R.id.movie_reservation_people_count_minus)).perform(click())

        // then
        val count = 1
        onView(withId(R.id.movie_reservation_people_count)).check(matches(withText(count.toString())))
    }
    @Test
    fun 카운터의_빼기_버튼을_눌러도_값이_1_미만으로_감소하지_않는다() {
        // given

        // when
        onView(withId(R.id.movie_reservation_people_count_minus)).perform(click())

        // then
        val count = 1
        onView(withId(R.id.movie_reservation_people_count)).check(matches(withText(count.toString())))
    }
}
