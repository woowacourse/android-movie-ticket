package woowacourse.movie.view.reservation

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.TestFixture.moviesFirstItem
import woowacourse.movie.view.home.ReservationHomeActivity
import java.time.LocalDate
import java.time.LocalTime

@RunWith(AndroidJUnit4::class)
class ReservationDetailActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(ReservationDetailActivity::class.java)

    @Before
    fun setUp() {
        activityRule.scenario.recreate()
    }

    @After
    fun tearDown() {
        activityRule.scenario.close()
    }

    @Test
    fun `티켓의_기본_수량은_1장이다`() {
        onView(withId(R.id.text_view_reservation_detail_number_of_tickets))
            .check(matches(withText("1")))
    }

    @Test
    fun `빼기_버튼을_누르면_티켓_수량이_1장_감소한다`() {
        // given
        onView(withId(R.id.button_reservation_detail_plus)).perform(click())

        // when
        onView(withId(R.id.button_reservation_detail_minus)).perform(click())

        // then
        onView(withId(R.id.text_view_reservation_detail_number_of_tickets)).check(matches(withText("1")))
    }

    @Test
    fun `더하기_버튼을_누르면_티켓_수량이_1장_증가한다`() {
        onView(withId(R.id.button_reservation_detail_plus)).perform(click())

        onView(withId(R.id.text_view_reservation_detail_number_of_tickets)).check(matches(withText("2")))
    }

    @Test
    fun `영화_상세_화면은_영화_홈_화면의_영화_목록_지금_예매_버튼을_누르면_보여진다`() {
        ActivityScenario.launch(ReservationHomeActivity::class.java)
        moviesFirstItem.onChildView(
            withId(R.id.item_movie_catalog_button_reservation),
        ).perform(click())

        onView(withId(R.id.constraint_layout_reservation_detail)).check(matches(isDisplayed()))
    }

    @Test
    fun `티켓_수를_2로_중가시킨_후_화면_회전_시_티켓_수가_그대로_유지_된다`() {
        // given
        onView(withId(R.id.button_reservation_detail_plus)).perform(click())

        // when
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.text_view_reservation_detail_number_of_tickets)).check(matches(withText("2")))
    }

    @Test
    fun `2024년_3월_4일의_상영일을_스피너로_선택하면_상영일_스피너에_2024-03-04_보여진다`() {
        // given
        onView(withId(R.id.spinner_reservation_detail_screening_date)).perform(click())
        onData(allOf(`is`(instanceOf(LocalDate::class.java)), `is`(LocalDate.of(2024, 3, 4)))).perform(click())
        // then
        onView(withId(R.id.spinner_reservation_detail_screening_date)).check(matches(withSpinnerText(containsString("2024-03-04"))))
    }

    @Test
    fun `12시의_상영시간을_스피너로_선택하면_상영시간_스피너에_선택한_상영_시간이_보여진다`() {
        onView(withId(R.id.spinner_reservation_detail_screening_time)).perform(click())
        onData(allOf(`is`(instanceOf(LocalTime::class.java)), `is`(LocalTime.of(12, 0)))).perform(click())
        onView(withId(R.id.spinner_reservation_detail_screening_time)).check(matches(withSpinnerText(containsString("12:00"))))
    }

    @Test
    fun `상영일을_평일로_선택하면_상영시간_스피너의_기본값은_10시이다`() {
        onView(withId(R.id.spinner_reservation_detail_screening_date)).perform(click())
        onData(allOf(`is`(instanceOf(LocalDate::class.java)), `is`(LocalDate.of(2024, 3, 1)))).perform(click())

        onView(withId(R.id.spinner_reservation_detail_screening_time)).perform(click())
        onData(allOf(`is`(instanceOf(LocalTime::class.java)), `is`(LocalTime.of(10, 0)))).perform(click())

        onView(withId(R.id.spinner_reservation_detail_screening_time)).check(matches(withSpinnerText(containsString("10:00"))))
    }

    @Test
    fun `상영일을_주말로_선택하면_상영시간_스피너의_기본값은_9시이다`() {
        onView(withId(R.id.spinner_reservation_detail_screening_date)).perform(click())
        onData(allOf(`is`(instanceOf(LocalDate::class.java)), `is`(LocalDate.of(2024, 3, 2)))).perform(click())

        onView(withId(R.id.spinner_reservation_detail_screening_time)).perform(click())
        onData(allOf(`is`(instanceOf(LocalTime::class.java)), `is`(LocalTime.of(9, 0)))).perform(click())

        onView(withId(R.id.spinner_reservation_detail_screening_time)).check(matches(withSpinnerText(containsString("09:00"))))
    }
}
