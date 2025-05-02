package woowacourse.movie.ui.seat

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagKey
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.ui.seat.view.BookingSeatActivity
import java.time.LocalDateTime

class BookingSeatActivityTest {
    @Before
    fun setUp() {
        val intent =
            BookingSeatActivity.newIntent(
                fakeContext,
                "해리 포터와 마법사의 돌",
                LocalDateTime.of(2025, 5, 1, 12, 0),
                Headcount(2),
            )
        ActivityScenario.launch<BookingSeatActivity>(intent)
    }

    @Test
    fun `영화_이름을_출력한다`() {
        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `선택된_좌석에_맞는_가격이_표시된다`() {
        onView(withId(R.id.tv_price)).check(matches(withText("0원")))

        onView(withTagKey(R.id.seat_tag, equalTo("A1"))).perform(click())
        onView(withId(R.id.tv_price)).check(matches(withText("10,000원")))

        onView(withTagKey(R.id.seat_tag, equalTo("C1"))).perform(click())
        onView(withId(R.id.tv_price)).check(matches(withText("25,000원")))

        onView(withTagKey(R.id.seat_tag, equalTo("A1"))).perform(click())
        onView(withId(R.id.tv_price)).check(matches(withText("15,000원")))
    }

    @Test
    fun `인원_수가_정확해야_확인_버튼이_활성화된다`() {
        // selected 0 : not enabled
        onView(withId(R.id.btn_confirm)).check(matches(not(isEnabled())))

        // selected 1 : not enabled
        onView(withTagKey(R.id.seat_tag, equalTo("A1"))).perform(click())
        onView(withId(R.id.btn_confirm)).check(matches(not(isEnabled())))

        // selected 2 : enabled
        onView(withTagKey(R.id.seat_tag, equalTo("C1"))).perform(click())
        onView(withId(R.id.btn_confirm)).check(matches(isEnabled()))
    }

    @Test
    fun `예매_선택_완료_버튼을_누르면_예매_확인_다이얼로그가_나타난다`() {
        // given
        onView(withTagKey(R.id.seat_tag, equalTo("A1"))).perform(click())
        onView(withTagKey(R.id.seat_tag, equalTo("A2"))).perform(click())

        // when
        onView(withId(R.id.btn_confirm)).perform(click())

        // then
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
    }

    @Test
    fun `다이얼로그_외부_영역을_클릭해도_다이얼로그가_유지된다`() {
        // given
        onView(withTagKey(R.id.seat_tag, equalTo("A1"))).perform(click())
        onView(withTagKey(R.id.seat_tag, equalTo("A2"))).perform(click())
        onView(withId(R.id.btn_confirm)).perform(click())

        // when
        pressBack()

        // then
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
    }
}
