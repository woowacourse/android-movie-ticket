package woowacourse.movie.java

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.view.MovieListActivity

@RunWith(AndroidJUnit4::class)
class MovieListTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    //    @Test
    //    fun `현재_텍스트_확인`() {
    //        onView(withId(R.id.movie_list_view))
    //            .check(matches(withText("해리 포터와 마법사의 돌")))
    //    }
    //
    //    @Test
    //    fun `빼기_버튼을_누르면`() {
    //        onView(withId(R.id.mtext))
    //            .perform(click())
    //
    //        onView(withId(R.id.count))
    //            .check(matches(withText("-1")))
    //    }
    //    @Test
    //    fun `현재_텍스트_확인`() {
    //        onView(withId(R.id.movie_list_view))
    //            .check(ViewAssertions.matches(withText("해리 포터와 마법사의 돌")))
    //    }

    @Test
    fun test() {
        onView(withId(R.id.movie_list_view))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun `예약_완료_버튼_클릭시_예약완료_화면으로_이동한다`() {
        onView(withId(R.id.movie_reservation_button))
            .perform(click())

        onView(withId(R.id.main))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}

// 2. 도메인 테스트 - 2번째 화면에서 인터페이스 정상 동작 테스트
// 3. intent가 값을 정확히 전달하는 지 테스트
// 4. Constract 2개 생성 + 테스트
// 5. 상수화, 클래스 분리
// 1. 뷰테스트 - 2번째 화면 - 동작 시 값 전환 테스트 + 클릭 시 화면 전환 테스트
//           - 3번째 화면 - 텍스트 일치 테스트
