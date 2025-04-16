package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `앱을_실행하면_영화_제목이_표시된다`() {
        onView(withId(R.id.tv_item_movie_title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `상영일이_표시된다`() {
        onView(withId(R.id.tv_item_movie_screening_date))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `목록에_영화_정보를_표시한다`() {
        onView(withId(R.id.lv_main_movies))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `지금_예매를_클릭하면_영화_예매_완료_화면이_보여진다`() {
        onView(withId(R.id.btn_item_movie_reserve))
            .perform(click())

        onView(withId(R.id.layout_reservation_result))
            .check(matches(isDisplayed()))
    }
}
