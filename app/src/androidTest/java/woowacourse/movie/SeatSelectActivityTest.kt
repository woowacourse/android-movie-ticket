package woowacourse.movie

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.util.Checks
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.seatselect.SeatSelectActivity
import java.time.LocalDateTime

class SeatSelectActivityTest {
    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), SeatSelectActivity::class.java).apply {
            putExtra(SeatSelectActivity.TITLE_KEY, "해리포터와 마법사의 돌 1")
            putExtra(SeatSelectActivity.DATETIME_KEY, LocalDateTime.of(2023, 3, 12, 12, 0, 0))
            putExtra(SeatSelectActivity.COUNT_KEY, 20)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectActivity>(intent)

    private val ids = listOf(
        R.id.seat_a1, R.id.seat_a2, R.id.seat_a3, R.id.seat_a4,
        R.id.seat_b1, R.id.seat_b2, R.id.seat_b3, R.id.seat_b4,
        R.id.seat_c1, R.id.seat_c2, R.id.seat_c3, R.id.seat_c4,
        R.id.seat_d1, R.id.seat_d2, R.id.seat_d3, R.id.seat_d4,
        R.id.seat_e1, R.id.seat_e2, R.id.seat_e3, R.id.seat_e4,
    )

    @Test
    fun 영화_제목을_띄운다() {
        onView(withId(R.id.text_title))
            .check(matches(withText("해리포터와 마법사의 돌 1")))
    }

    @Test
    fun 아직_선택하지_않은_좌석을_클릭하면_색깔이_바뀐다() {
        ids.forEach {
            onView(withId(it)).perform(ViewActions.click())
            onView(withId(it)).check(matches(isSelected()))
            onView(withId(it)).check(matches(withBackgroundColor(R.color.select_seat)))
        }
    }

    @Test
    fun 이미_선택한_좌석을_클릭하면_원래_색깔로_바뀐다() {
        ids.forEach {
            onView(withId(it)).perform(ViewActions.click())
        }
        ids.forEach {
            onView(withId(it)).perform(ViewActions.click())
            onView(withId(it)).check(matches(not(isSelected())))
            onView(withId(it)).check(matches(withBackgroundColor(R.color.white)))
        }
    }

    @Test
    fun 예매할_모든_좌석을_선택한_경우_버튼이_활성화된다() {
        ids.forEach {
            onView(withId(it)).perform(ViewActions.click())
        }
        onView(withId(R.id.btn_next)).check(matches(isEnabled()))
    }

    @Test
    fun 예매할_모든_좌석을_선택하기_전까지_버튼이_활성화되지_않는다() {
        ids.drop(1).forEach {
            onView(withId(it)).perform(ViewActions.click())
        }
        onView(withId(R.id.btn_next)).check(matches(isNotEnabled()))
    }

    @Test
    fun 좌석을_클릭하면_가격이_올라간다() {
        ids.forEach {
            onView(withId(it)).perform(ViewActions.click())
        }
        onView(withId(R.id.text_price)).check(matches(withText("248,000원")))
    }

    @Test
    fun 좌석_선택을_해제하면_가격이_내려간다() {
        onView(withId(R.id.seat_a1)).perform(ViewActions.click())
        onView(withId(R.id.seat_a1)).perform(ViewActions.click())
        onView(withId(R.id.text_price)).check(matches(withText("0원")))
    }

    private fun withBackgroundColor(@ColorInt color: Int): Matcher<View> {
        Checks.checkNotNull(color)
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("TextView background color : $color")
            }

            override fun matchesSafely(item: TextView?): Boolean {
                val backgroundColor = item?.background as ColorDrawable
                val colorDrawable = ColorDrawable(ContextCompat.getColor(item.context, color))
                return colorDrawable.color == backgroundColor.color
            }
        }
    }
}
