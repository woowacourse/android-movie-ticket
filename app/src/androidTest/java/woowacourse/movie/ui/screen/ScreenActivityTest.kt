package woowacourse.movie.ui.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class ScreenActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<ScreenActivity> = ActivityScenarioRule(ScreenActivity::class.java)

    @Test
    fun 상영_목록이_표시된다() {
        onView(withId(R.id.lv_screen)).check(matches(isDisplayed()))
    }

    // TODO: 리스트뷰의 아이템이 여러 개일 때 에러
//    @Test
//    fun 영화_제목이_표시된다() {
//        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun 상영_날짜가_표시된다() {
//        onView(withId(R.id.tv_screen_date)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun 상영일이_표시된다() {
//        onView(withId(R.id.tv_screen_date)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun 상영_포스터가_표시된다() {
//        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun 상영_러닝_타임이_표시된다() {
//        onView(withId(R.id.tv_screen_running_time)).check(matches(isDisplayed()))
//    }
}
